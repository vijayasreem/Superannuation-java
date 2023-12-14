package com.lic.epgs.quotation.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "QUO_MBR_APOTE")
public class QuotationMemberAppointeeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUO_MBR_APOTE_SEQ")
	@SequenceGenerator(name = "QUO_MBR_APOTE_SEQ", sequenceName = "QUO_MBR_APOTE_SEQ", allocationSize = 1)
	@Column(name = "APPOINTEE_ID", length = 10)
	private Long appointeeId;

	@Column(name = "MEMBER_ID", length = 10)
	private Long memberId;

	@Column(name = "APPOINTEE_NAME")
	private String appointeeName;

	@Column(name = "APPOINTEE_CODE")
	private String appointeeCode;

	@Column(name = "RELATIONSHIP")
	private String relationship;

	@Column(name = "CONTACT_NUMBER")
	private Long contactNumber;

	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;

	@Column(name = "APPOINTEE_PAN")
	private String pan;

	@Column(name = "APPOINTEE_AADHAR_NUMBER")
	private Long aadharNumber;

	@Column(name = "APPOINTEE_IFSC_CODE")
	private String ifscCode;

	@Column(name = "APPOINTEE_BANK_NAME")
	private String bankName;

	@Column(name = "APPOINTEE_ACCOUNT_TYPE")
	private String accountType;

	@Column(name = "APPOINTEE_ACCOUNT_NUMBER")
	private Long accountNumber;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive = Boolean.TRUE;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_ON")
	private Date createdOn = new Date();

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "MODIFIED_ON")
	private Date modifiedOn = new Date();

}
