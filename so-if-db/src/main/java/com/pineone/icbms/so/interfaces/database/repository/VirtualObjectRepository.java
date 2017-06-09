package com.pineone.icbms.so.interfaces.database.repository;

import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * VirtualObjectForDB repository(DAO for table) interface.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 13..
 */
@Repository
@Table(name = "virtual_object")

public interface VirtualObjectRepository extends JpaRepository<VirtualObjectForDB, String> {
    // using JPA Query methods

    // id 로 조회
    VirtualObjectForDB findById(String id);

    // functionality_id, aspect_id 를 and 조건으로 조회
    List<VirtualObjectForDB> findByFunctionalityIdAndAspectId(String functionalityId, String aspectId);

    List<VirtualObjectForDB> findByNameContaining(String name);
}
