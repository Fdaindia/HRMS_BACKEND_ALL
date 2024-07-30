package com.fdaindia.hrms.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "onboarding")
public class Onboarding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "employment_type")
	private String employmentType;

	@Column(name = "job_title")
	private String jobTitle;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "nationality")
	private String nationality;

	@JsonManagedReference
	@OneToOne
	private Employee employee;

	@Column(name = "identity_proof_path")
	private String identityProofPath;

	@Column(name = "address_proof_path")
	private String addressProofPath;

	@Column(name = "educational_certificates_path")
	private String educationalCertificatesPath;

	@Column(name = "previous_employment_documents_path")
	private String previousEmploymentDocumentsPath;

	@Column(name = "bank_account_details_path")
	private String bankAccountDetailsPath;

	@Column(name = "offer_letter_path")
	private String offerLetterPath;

}
