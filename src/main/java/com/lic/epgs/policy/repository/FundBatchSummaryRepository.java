package com.lic.epgs.policy.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lic.epgs.policy.entity.FundBatchSummaryEntity;

@Repository
public interface FundBatchSummaryRepository extends JpaRepository<FundBatchSummaryEntity, BigDecimal> {

	@Query(value = "select * from FUND_BATCH_SUMMARY_1 where batch_no =:batchNo ", nativeQuery = true)
	List<FundBatchSummaryEntity> findByBatchNo(String batchNo);

}
