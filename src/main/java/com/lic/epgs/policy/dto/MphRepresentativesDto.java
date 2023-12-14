package com.lic.epgs.policy.dto;
/**
 * @author pradeepramesh
 *
 */
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MphRepresentativesDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long repId;
	private Long mphId;
	private String representativeCode;
	private String representativeName;
	private Long landlineNo;
	private String countryCode;
	private Long mobileNo;
	private String emailId;
	private String addressType;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String cityLocality;
	private String district;
	private String stateName;
	private Integer pincode;
	private Boolean isActive;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "asia/kolkata")
	private Date createdOn;
	private String createdBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "asia/kolkata")
	private Date modifiedOn;
	private String modifiedBy;
}
