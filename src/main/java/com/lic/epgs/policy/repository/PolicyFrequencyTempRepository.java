package com.lic.epgs.policy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lic.epgs.policy.entity.PolicyFrequencyDetailsTempEntity;

public interface PolicyFrequencyTempRepository extends JpaRepository<PolicyFrequencyDetailsTempEntity, Long> {


	List<PolicyFrequencyDetailsTempEntity> findAllByPolicyIdAndStatus(Long policyId, String string);

	PolicyFrequencyDetailsTempEntity findByPolicyId(Long policyId);


	List<PolicyFrequencyDetailsTempEntity> findAllByPolicyIdOrderByFrequencyDatesAsc(Long policyId);

	List<PolicyFrequencyDetailsTempEntity> findAllByPolicyIdAndStatusOrderByFrequencyDatesAsc(Long policyId, String unpaid);

 	 List<PolicyFrequencyDetailsTempEntity> findAllByPolicyId(Long policyId);

	List<PolicyFrequencyDetailsTempEntity> findAllByPolicyIdAndStatusOrderByFrequencyDatesDesc(Long policyId,String unpaid);

	List<PolicyFrequencyDetailsTempEntity> findAllByPolicyIdAndStatusOrderByFrequencyIdAsc(Long policyId, String unpaid);

	List<PolicyFrequencyDetailsTempEntity> findAllByPolicyIdAndStatusOrderByFrequencyIdDesc(Long policyId, String unpaid);


}
