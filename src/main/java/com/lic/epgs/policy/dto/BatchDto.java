package com.lic.epgs.policy.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//import com.lic.epgs.sa.fund.interest.member.v2.entity.MemberFundHistoryV2Entity;
//import com.lic.epgs.sa.fund.interest.policy.v2.entity.PolicyFundHistoryV2Entity;
//import com.lic.epgs.sa.fund.interest.search.service.impl.SearchConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Muruganandam
 *
 */

//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class BatchDto implements Serializable {
	
	

	
	private static final long serialVersionUID = 1L;

	@JsonIgnore(value = true)
	private Long id;

	private Long policyId;

	@JsonProperty(value = "licId")
	private String memberId;

	@JsonIgnore(value = true)
	private Long policyMemberId;

	@JsonIgnore(value = true)
	private Long summStmtId;

	private List<Long> policyIds;

	@JsonProperty(value = "licIds")
	private List<String> memberIds;

	private String policyNumber;

	private String trnxDate;

	@JsonIgnore(value = true)
	private String depositDate;

	@JsonIgnore(value = true)
	private String modifiedBy;

	@JsonIgnore(value = true)
	private String createdBy;

	@JsonIgnore(value = true)
	private String createdOn;

	@JsonIgnore(value = true)
	private String remarks;

	private String variant;

	private String policyType;

	@JsonIgnore(value = true)
	private String batchType;

	@JsonIgnore(value = true)
	private @Default Boolean isExit = false;

	/** @JsonIgnore(value = true) */
	private String txnType;

	/** @JsonIgnore(value = true) */
	private String txnSubType;

	@JsonIgnore(value = true)
	private @Default Boolean isTxn = false;

	@JsonIgnore(value = true)
	private @Default Boolean isActive = true;

	@JsonIgnore(value = true)
	private @Default Boolean isExecuted = true;

	private @Default Boolean isBatch = false;

	private @Default Boolean isAuto = true;

	private @Default Boolean setOpeningBalance = false;

	private @Default Boolean recalculate = false;

	@JsonIgnore(value = true)
	private BigDecimal totalAverageBalanceAmount;

	@JsonIgnore(value = true)
	private String batchTime;

	@JsonIgnore(value = true)
	private @Default Double interestRate = 0.0;

	@JsonIgnore(value = true)
	private @Default BigDecimal txnAmount = BigDecimal.ZERO;

	@JsonIgnore(value = true)
	private List<InterestFundResponseDto> fundResponse;

	@JsonIgnore(value = true)
	private InterestFundResponseDto fundResponseDetails;

//	@JsonIgnore(value = true)
//	private transient List<PolicyFundHistoryV2Entity> policies;
//
//	@JsonIgnore(value = true)
//	private transient List<MemberFundHistoryV2Entity> members;

	@JsonIgnore(value = true)
	private transient Map<String, Object> batch;

	@JsonIgnore(value = true)
	private Integer total;

	private String unitId;

	private String batchNumber;

	@JsonIgnore(value = true)
	private String memberName;

	@JsonIgnore(value = true)
	private String mphName;

	@JsonIgnore(value = true)
	private Integer index;

	private String tranFromDate;
	private String tranToDate;
//	private @Default String memberStatus = SearchConstants.ACTIVE;
//
//	private @Default List<String> policyStatus = java.util.Arrays.asList(SearchConstants.POLSRV_APPROVEDS_STRING,
//			SearchConstants.POLSRV_PAID_UP);
}