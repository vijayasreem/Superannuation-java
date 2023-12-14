package com.lic.epgs.policy.repository;
/**
 * @author pradeepramesh
 *
 */
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lic.epgs.policy.entity.MphAddressEntity;

@Repository
public interface MphAddressRepository extends JpaRepository<MphAddressEntity, Long> {

	Set<MphAddressEntity> findAllByMphIdAndIsActive(Long mphId, Boolean true1);
	
	MphAddressEntity findByMphIdAndIsActiveTrue(Long mphId);
	
	
	@Query(value = "SELECT padd.STATE_ID,mm.MPH_ID,\r\n"
			+ "pm.POLICY_ID,pm.POLICY_NUMBER,\r\n"
			+ "pm.POLICY_STATUS,pm.POLICY_TYPE \r\n"
			+ "FROM POLICY_MASTER pm JOIN MPH_MASTER mm ON (mm.MPH_ID =pm.MPH_ID) WHERE pm.POLICY_NUMBER =:policyNumber and pm.IS_ACTIVE= '1' AND "
			+ "JOIN MPH_ADDRESS padd  ON (mm.MPH_ID = padd.MPH_ID) WHERE IS_ACTIVE = '1'", nativeQuery = true)
	Object[] getAddressDetails(String policyNumber);

	MphAddressEntity findByMphIdAndIsActiveTrueAndIsDefaultTrue(Long mphId);
	
//	@Query(value="SELECT ADDRESS_LINE1  FROM MPH_ADDRESS ma WHERE MPH_ID =:MPH_ID AND IS_ACTIVE =1 AND IS_DEFAULT =1",nativeQuery = true)
//	String getAddressByMphId(Long mphId);
	
	@Query(value="SELECT ADDRESS_LINE1  FROM MPH_ADDRESS ma WHERE MPH_ID =:mphId AND IS_ACTIVE =1 AND IS_DEFAULT =1", nativeQuery=true)
	String getAddressByMphId(@Param("mphId")Long mphId);

	@Query(value ="select ma.state_id from policy_master pm join mph_master mm on mm.mph_id=pm.mph_id\r\n"
			+ "join mph_address ma on ma.mph_id=mm.mph_id where \r\n"
			+ "pm.policy_number=?1 and pm.is_active=1 and pm.policy_status in (4,11)", nativeQuery = true)
	Integer getStateId(@Param ("policyNumber") String policyNumber);
	
	@Query(value = "SELECT EMAIL_ID FROM  liccommon.master_unit WHERE UNIT_CODE = :unitCode", nativeQuery = true)
	String getEmailId(String unitCode);
}
