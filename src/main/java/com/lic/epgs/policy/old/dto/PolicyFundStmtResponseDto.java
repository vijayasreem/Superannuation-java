package com.lic.epgs.policy.old.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyFundStmtResponseDto {
	
	
	private String unitId;
	private String variant;
	private String financialYear;
	private String quarter;	
	private List<PolicyStmtDto> listOfPolicies;
	

}
