package com.fdaindia.hrms.tokenconfig;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.fdaindia.hrms.entity.Employee;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60; // 1 day

    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getClaimFromToken(token, Claims::getExpiration);
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private Boolean isTokenValid(String token, int type) {
        try {
            String usrType = getClaimFromToken(token, Claims::getAudience);
            if ((type == 1 && usrType.equalsIgnoreCase("api")) ||
                (type == 2 && usrType.equalsIgnoreCase("department")) ||
                (type == 3 && (usrType.equalsIgnoreCase("hrms") || usrType.equalsIgnoreCase("api") || usrType.equalsIgnoreCase("department") ||
                               usrType.equalsIgnoreCase("designation") || usrType.equalsIgnoreCase("employee") || 
                               usrType.equalsIgnoreCase("holidaymaster") || usrType.equalsIgnoreCase("hrPolicy") || usrType.equalsIgnoreCase("request") ||
                               usrType.equalsIgnoreCase("type")))) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private Boolean ignoreTokenExpiration(String token) {
        return false;
    }

    public String generateToken(Employee employee) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", employee.getUsername());
        claims.put("aud", "hrms");

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS512).compact();
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("aud", "hrms");

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS512).compact();
    }

    public String generateTokenWithSessionId(Employee employee, String sessionId, Date sessionExpiry) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", employee.getUsername());
        claims.put("aud", "hrms");
        claims.put("sessionId", sessionId);
        claims.put("role", employee.getRole());

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(sessionExpiry)
                .signWith(secretKey, SignatureAlgorithm.HS512).compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public Boolean validateToken(String token, int type) {
        try {
            if (!isTokenExpired(token)) {
                return isTokenValid(token, type);
            }
            return false;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException
                | IllegalArgumentException ex) {
            if (ex instanceof SignatureException) {
                throw new SignatureException("Invalid JWT-Token signature!");
            } else if (ex instanceof MalformedJwtException) {
                throw new MalformedJwtException("Jwt-token Structure is invalid");
            } else if (ex instanceof ExpiredJwtException) {
                throw new ExpiredJwtException(null, null, "jwt-token has expired, Please try to login with new token");
            } else if (ex instanceof UnsupportedJwtException) {
                throw new UnsupportedJwtException("Unsupported Jwt-token format");
            } else if (ex instanceof IllegalArgumentException) {
                throw new IllegalArgumentException("Invalid argument found when processing the Jwt-token");
            }
            throw ex;
        }
    }
}
