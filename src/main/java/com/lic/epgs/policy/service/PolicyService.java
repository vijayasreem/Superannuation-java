package com.lic.epgs.policy.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import com.lic.epgs.adjustmentcontribution.dto.AdjustmentContributionResponseDto;
import com.lic.epgs.common.exception.ApplicationException;
import com.lic.epgs.policy.dto.CommonResponseStampDto;
import com.lic.epgs.policy.dto.ContributionRequestDto;
import com.lic.epgs.policy.dto.GetPolicyMemberDetailsRequestDto;
import com.lic.epgs.policy.dto.IssuePolicyResponseDto;
import com.lic.epgs.policy.dto.PolicyDetailSearchRequestDto;
import com.lic.epgs.policy.dto.PolicyDetailSearchResponseDto;
import com.lic.epgs.policy.dto.PolicyDetailsResponseDto;
import com.lic.epgs.policy.dto.PolicyFrequencyDetailsDto;
import com.lic.epgs.policy.dto.PolicyNotesDto;
import com.lic.epgs.policy.dto.PolicySearchDto;
import com.lic.epgs.policy.dto.ProposalAnnuityDto;
import com.lic.epgs.policy.dto.StampDutyConsumptionRequestDto;
import com.lic.epgs.policy.dto.TrnRegistrationResponseDto;
import com.lic.epgs.policy.entity.FundBatchSummaryEntity;
import com.lic.epgs.policy.old.dto.JsonResponse;
import com.lic.epgs.policy.old.dto.PolicyAddressOldDto;
import com.lic.epgs.policy.old.dto.PolicyAdjustmentRequestDto;
import com.lic.epgs.policy.old.dto.PolicyAdjustmentResponse;
import com.lic.epgs.policy.old.dto.PolicyBankOldDto;
import com.lic.epgs.policy.old.dto.PolicyDto;
import com.lic.epgs.policy.old.dto.PolicyFundStmtRequestDto;
import com.lic.epgs.policy.old.dto.PolicyFundStmtResponseDto;
import com.lic.epgs.policy.old.dto.PolicyResponseDto;
import com.lic.epgs.policy.old.dto.PolicyStmtGenReqDto;
import com.lic.epgs.policy.old.dto.PolicyStmtGenRespDto;
import com.lic.epgs.regularadjustmentcontribution.dto.RegularAdjustmentContributionResponseDto;
import com.lic.epgs.policy.dto.DepositRefundPolicySearchRequestDto;

public interface PolicyService {

	PolicyDetailsResponseDto getPolicyDetailsByProposalNo(String proposalNo);

	PolicyResponseDto savePolicyDetails(PolicyDto policyDto) throws ParseException;

	PolicyResponseDto saveAddressDetails(PolicyAddressOldDto addressDto);

	PolicyResponseDto getAddressList(Long mphId);

	PolicyResponseDto saveBankDetails(PolicyBankOldDto bankDto);

	PolicyResponseDto getBankList(Long mphId);

	PolicyAdjustmentResponse saveAdjustment(PolicyAdjustmentRequestDto adjustmentDto);

	PolicyResponseDto saveNotesDetails(PolicyNotesDto policyNotesDto);

	PolicyResponseDto getNoteList(Long policyId);

	PolicyResponseDto inprogressCitrieaSearch(PolicySearchDto policySearchDto);

	PolicyResponseDto getPolicyById(String inprogress, Long mphId);

	PolicyResponseDto existingCitrieaSearch(PolicySearchDto policySearchDto);

	PolicyResponseDto getExistingPolicyByPolicyNumber(String policyNumber, String unitId);

	PolicyResponseDto getExistingPolicyBymphId(String existing, Long mphId, Long policyId);

	PolicyResponseDto changeStatus(Long policyId, String checkerNo);

	PolicyResponseDto policyApproved(Long policyId, String approved, String variantType) throws ApplicationException;

	PolicyResponseDto policyReject(Long policyId, String reject);

	BigDecimal calculateStamps(BigDecimal amount);

	CommonResponseStampDto stampDutyConsuption(StampDutyConsumptionRequestDto requestDto) throws ApplicationException;

	RegularAdjustmentContributionResponseDto getFrequencyDates(PolicyFrequencyDetailsDto request) throws ParseException;

	AdjustmentContributionResponseDto getFrequencyByPolicyId(Long policyId);

	PolicyResponseDto getPolicyMember(Long memberId, Long policyId);

	PolicyResponseDto getMemberDetails(String licId, Long policyId);

	Object getPolicyMemberDetailsDataTable(GetPolicyMemberDetailsRequestDto getPolicyMemberDetailsRequestDto);

	PolicyResponseDto getPolicyContributionDetails(ContributionRequestDto contributionRequestDto);

	PolicyResponseDto getMemberContributionDetails(ContributionRequestDto contributionRequestDto);

	PolicyResponseDto getInvidualContriButionDetails(ContributionRequestDto contributionRequestDto);

	InputStream policyContributionSummary(Long policyId, Long adjConId) throws IOException;

	InputStream getFullContribution(Long policyId, String finicialYear);

	IssuePolicyResponseDto issuancePolicy(String policyNumber);

	TrnRegistrationResponseDto trnRegistration(String policyNumber);

	PolicyResponseDto checkissuancePolicySuccessOrNot(String policyNumber);

	List<PolicyDetailSearchResponseDto> policyDetailSearch(PolicyDetailSearchRequestDto policyDetailSearchRequestDto);

	InputStream membersDownload(Long policyId, Long mphId, String unitCode) throws IOException;

	PolicyResponseDto getMemberDetailsByLicIdandMemberId(String licId, Long policyId, String membershipNumber);

	PolicyResponseDto savePolicyOldDto(Long mphId);

	PolicyAdjustmentResponse saveAdjustmentOldDto(Long policyId);


	ProposalAnnuityDto getProposalDetails(String proposalNumber);
	PolicyFundStmtResponseDto fetchDetailsFrPolicyFundStatement(PolicyFundStmtRequestDto requestDto);

	PolicyStmtGenRespDto generateFundStatement(PolicyStmtGenReqDto requestDto);

	JsonResponse fetchBatchStatus(String batchNo);
	
	CommonResponseStampDto DepsoitRefundPolicySearch(DepositRefundPolicySearchRequestDto dto);
	
}
