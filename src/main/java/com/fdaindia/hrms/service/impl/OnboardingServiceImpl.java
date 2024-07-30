package com.fdaindia.hrms.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fdaindia.hrms.entity.Employee;
import com.fdaindia.hrms.entity.Onboarding;
import com.fdaindia.hrms.exceptions.ResourceNotFoundException;
import com.fdaindia.hrms.repository.OnbordingRepo;
import com.fdaindia.hrms.response.FinalResponse;
import com.fdaindia.hrms.service.OnbordingService;

@Service
public class OnboardingServiceImpl implements OnbordingService {

	@Autowired
	private OnbordingRepo onboardingRepo;

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Override
	public FinalResponse saveOnboarding(Onboarding onboarding, MultipartFile identityProof, MultipartFile addressProof,
			MultipartFile educationalCertificates, MultipartFile previousEmploymentDocuments,
			MultipartFile bankAccountDetails, MultipartFile offerLetter) throws IOException {

		saveFileAndSetPath(identityProof, onboarding, onboarding::setIdentityProofPath);
		saveFileAndSetPath(addressProof, onboarding, onboarding::setAddressProofPath);
		saveFileAndSetPath(educationalCertificates, onboarding, onboarding::setEducationalCertificatesPath);
		saveFileAndSetPath(previousEmploymentDocuments, onboarding, onboarding::setPreviousEmploymentDocumentsPath);
		saveFileAndSetPath(bankAccountDetails, onboarding, onboarding::setBankAccountDetailsPath);
		saveFileAndSetPath(offerLetter, onboarding, onboarding::setOfferLetterPath);
		Onboarding savedOnboarding = onboardingRepo.save(onboarding);

		FinalResponse response = new FinalResponse();
		response.setStatus(true);
		response.setMessage("Onboarding record saved successfully");
		response.setObject(savedOnboarding);
		return response;
	}

	@Override
	public FinalResponse updateOnboarding(Long id, Onboarding updatedOnboarding, MultipartFile identityProof,
			MultipartFile addressProof, MultipartFile educationalCertificates,
			MultipartFile previousEmploymentDocuments, MultipartFile bankAccountDetails, MultipartFile offerLetter)
			throws IOException {

		Onboarding existingOnboarding = onboardingRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Onboarding not found with id: " + id, uploadDir));

		existingOnboarding.setFullName(updatedOnboarding.getFullName());
		existingOnboarding.setEmploymentType(updatedOnboarding.getEmploymentType());
		existingOnboarding.setJobTitle(updatedOnboarding.getJobTitle());
		existingOnboarding.setMaritalStatus(updatedOnboarding.getMaritalStatus());
		existingOnboarding.setNationality(updatedOnboarding.getNationality());
		// Retain the existing Employee object if not updating
		if (updatedOnboarding.getEmployee() != null) {
			existingOnboarding.setEmployee(updatedOnboarding.getEmployee());
		}
		saveFileAndSetPath(identityProof, existingOnboarding, existingOnboarding::setIdentityProofPath);
		saveFileAndSetPath(addressProof, existingOnboarding, existingOnboarding::setAddressProofPath);
		saveFileAndSetPath(educationalCertificates, existingOnboarding,
				existingOnboarding::setEducationalCertificatesPath);
		saveFileAndSetPath(previousEmploymentDocuments, existingOnboarding,
				existingOnboarding::setPreviousEmploymentDocumentsPath);
		saveFileAndSetPath(bankAccountDetails, existingOnboarding, existingOnboarding::setBankAccountDetailsPath);
		saveFileAndSetPath(offerLetter, existingOnboarding, existingOnboarding::setOfferLetterPath);
		onboardingRepo.save(existingOnboarding);

		return new FinalResponse("Onboarding record updated successfully", existingOnboarding, true);
	}

	@Override
	public void saveFileAndSetPath(MultipartFile file, Onboarding onboarding, Consumer<String> pathSetter)
			throws IOException {
		if (file != null && !file.isEmpty()) {
			if (onboarding.getEmployee() == null) {
				throw new IllegalArgumentException("Employee must be set in the onboarding object");
			}
			String filePath = saveFile(file, onboarding.getEmployee());
			pathSetter.accept(filePath);
		}
	}

	public String saveFile(MultipartFile file, Employee employee) throws IOException {
		if (employee == null) {
			throw new IllegalArgumentException("Employee must not be null");
		}
		String fileName = employee.getId() + "_"
				+ (employee.getName() != null ? employee.getName().replaceAll(" ", "_") : "unknown") + "_"
				+ file.getOriginalFilename();
		File directory = new File(uploadDir);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		File dest = new File(directory, fileName);
		file.transferTo(dest);
		return dest.getAbsolutePath();
	}

	public Onboarding getOnboardingById(Long id) {
		return onboardingRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Onboarding", "id", id));
	}

	public List<Onboarding> getAllOnboardings() {
		List<Onboarding> onboardings = onboardingRepo.findAll();
		return onboardings.stream().filter(onboarding -> onboarding.getEmploymentType() != null)
				.sorted(Comparator.comparing(Onboarding::getFullName)).collect(Collectors.toList());
	}

	public void deleteOnboarding(Long id) {
		Onboarding onboarding = onboardingRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Onboarding", "id", id));
		onboardingRepo.delete(onboarding);
	}

}
