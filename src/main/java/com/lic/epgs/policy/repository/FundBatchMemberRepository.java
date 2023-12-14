package com.lic.epgs.policy.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lic.epgs.policy.entity.FundBatchMemberEntity;

@Repository
public interface FundBatchMemberRepository extends JpaRepository<FundBatchMemberEntity, BigDecimal> {

	@Query(value = "SELECT * from FUND_BATCH_MEMBER_1 where batch_no =:batchNo ", nativeQuery=true)
	List<FundBatchMemberEntity> findByBatchNo(String batchNo);
	
	@Query(value = "SELECT * from FUND_BATCH_MEMBER_1 where batch_no =:batchNo and member_id =:memberId ", nativeQuery=true)
	FundBatchMemberEntity findByBatchNoMemberId(String batchNo, String memberId);

}
