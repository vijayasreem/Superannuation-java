package com.lic.epgs.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lic.epgs.common.entity.CommonNomineeRelationShipEntity;

@Repository
public interface CommonNomineeRelationShipRepository extends JpaRepository<CommonNomineeRelationShipEntity, Long>{

	List<CommonNomineeRelationShipEntity> findAllByStatusTrue();

}
