package com.fdaindia.hrms.service;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.web.multipart.MultipartFile;

import com.fdaindia.hrms.entity.Employee;
import com.fdaindia.hrms.entity.Onboarding;
import com.fdaindia.hrms.response.FinalResponse;

public interface OnbordingService {

	public FinalResponse saveOnboarding(Onboarding onboarding, MultipartFile identityProof, MultipartFile addressProof,
			MultipartFile educationalCertificates, MultipartFile previousEmploymentDocuments,
			MultipartFile bankAccountDetails, MultipartFile offerLetter) throws IOException;

	public FinalResponse updateOnboarding(Long id, Onboarding updatedOnboarding, MultipartFile identityProof,
			MultipartFile addressProof, MultipartFile educationalCertificates,
			MultipartFile previousEmploymentDocuments, MultipartFile bankAccountDetails, MultipartFile offerLetter)
			throws IOException;

	public void saveFileAndSetPath(MultipartFile file, Onboarding onboarding, Consumer<String> pathSetter)
			throws IOException;

	public String saveFile(MultipartFile file, Employee employee) throws IOException;

	public Onboarding getOnboardingById(Long id);

	public List<Onboarding> getAllOnboardings();

	public void deleteOnboarding(Long id);
}
