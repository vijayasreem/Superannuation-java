package com.lic.epgs.policy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lic.epgs.policy.entity.PolicyFrequencyDetailsEntity;

public interface PolicyFrequencyRepository extends JpaRepository<PolicyFrequencyDetailsEntity, Long> {


	List<PolicyFrequencyDetailsEntity> findAllByPolicyIdAndStatus(Long policyId, String string);

	PolicyFrequencyDetailsEntity findByPolicyId(Long policyId);


	List<PolicyFrequencyDetailsEntity> findAllByPolicyIdOrderByFrequencyDatesAsc(Long policyId);

	List<PolicyFrequencyDetailsEntity> findAllByPolicyIdAndStatusOrderByFrequencyDatesAsc(Long policyId, String unpaid);

 	 List<PolicyFrequencyDetailsEntity> findAllByPolicyId(Long policyId);

	List<PolicyFrequencyDetailsEntity> findAllByPolicyIdAndStatusOrderByFrequencyDatesDesc(Long policyId,String unpaid);

	List<PolicyFrequencyDetailsEntity> findAllByPolicyIdAndStatusOrderByFrequencyIdAsc(Long policyId, String unpaid);

	List<PolicyFrequencyDetailsEntity> findAllByPolicyIdAndStatusOrderByFrequencyIdDesc(Long policyId, String unpaid);


}
