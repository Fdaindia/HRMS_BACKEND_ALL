package com.fdaindia.hrms.request;



public class PunchRequest {
    private String action;
    private double latitude;
    public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	private double longitude;

    // Getters and Setters
}

