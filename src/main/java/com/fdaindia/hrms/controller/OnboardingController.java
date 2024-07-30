package com.fdaindia.hrms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fdaindia.hrms.entity.Onboarding;
import com.fdaindia.hrms.response.FinalResponse;
import com.fdaindia.hrms.service.OnbordingService;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

	@Autowired
	private OnbordingService onboardingService;

	@PostMapping("/save")
	public ResponseEntity<FinalResponse> saveOnboarding(@RequestPart("onboarding") Onboarding onboarding,
			@RequestPart(value = "identityProof", required = false) MultipartFile identityProof,
			@RequestPart(value = "addressProof", required = false) MultipartFile addressProof,
			@RequestPart(value = "educationalCertificates", required = false) MultipartFile educationalCertificates,
			@RequestPart(value = "previousEmploymentDocuments", required = false) MultipartFile previousEmploymentDocuments,
			@RequestPart(value = "bankAccountDetails", required = false) MultipartFile bankAccountDetails,
			@RequestPart(value = "offerLetter", required = false) MultipartFile offerLetter) throws IOException {

		FinalResponse response = onboardingService.saveOnboarding(onboarding, identityProof, addressProof,
				educationalCertificates, previousEmploymentDocuments, bankAccountDetails, offerLetter);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<FinalResponse> updateOnboarding(@PathVariable Long id,
			@RequestPart("onboarding") Onboarding onboarding,
			@RequestPart(value = "identityProof", required = false) MultipartFile identityProof,
			@RequestPart(value = "addressProof", required = false) MultipartFile addressProof,
			@RequestPart(value = "educationalCertificates", required = false) MultipartFile educationalCertificates,
			@RequestPart(value = "previousEmploymentDocuments", required = false) MultipartFile previousEmploymentDocuments,
			@RequestPart(value = "bankAccountDetails", required = false) MultipartFile bankAccountDetails,
			@RequestPart(value = "offerLetter", required = false) MultipartFile offerLetter) throws IOException {
		FinalResponse response = onboardingService.updateOnboarding(id, onboarding, identityProof, addressProof,
				educationalCertificates, previousEmploymentDocuments, bankAccountDetails, offerLetter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<FinalResponse> getOnboardingById(@PathVariable Long id) {
		Onboarding onboarding = onboardingService.getOnboardingById(id);
		FinalResponse response = new FinalResponse("Onboarding record retrieved successfully", onboarding, true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<FinalResponse> getAllOnboardings() {
		List<Onboarding> onboardings = onboardingService.getAllOnboardings();
		FinalResponse response = new FinalResponse("Onboarding records retrieved successfully", onboardings, true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<FinalResponse> deleteOnboarding(@PathVariable Long id) {
		onboardingService.deleteOnboarding(id);
		FinalResponse response = new FinalResponse("Onboarding record deleted successfully", null, true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
