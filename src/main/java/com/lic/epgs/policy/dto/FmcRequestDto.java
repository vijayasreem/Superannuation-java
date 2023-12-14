package com.lic.epgs.policy.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FmcRequestDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String unitId;
	
	private Double creditFmcAmount;
	private Double creditGstAmount;
	private BigDecimal debitFmcWithGstAmount;
	private Integer iCodeForBusinessSegment;
	private Integer iCodeForBusinessType;
	private Integer iCodeForInvestmentPortfolio;
	private Integer iCodeForLob;
	private Integer iCodeForParticipatingType;
	private Integer iCodeForProductLine;
	private String iCodeForVariant;
	private String mphCode;
	private String operatingUnitType;
	private String policyNo;
	private String productCode;
	private String unitCode;
	private String userCode;
	private String variantCode;
	
	private GstDetailsModelDto gstDetailModel;
	
	
	

}
