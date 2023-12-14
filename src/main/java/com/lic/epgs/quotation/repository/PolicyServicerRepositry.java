package com.lic.epgs.quotation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lic.epgs.policyservicing.common.entity.PolicyServiceEntity;

public interface PolicyServicerRepositry extends JpaRepository<PolicyServiceEntity, Long>{

	List<PolicyServiceEntity> findAllByPolicyIdAndIsActiveTrue(Long policyId);
}
