package com.pineone.icbms.so.interfaces.database.repository;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * CompositeVirtualObjectForDB repository(DAO for table) interface.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 13..
 */
@Repository
@Table(name = "composite_virtual_object")
public interface CompositeVirtualObjectRepository extends JpaRepository<CompositeVirtualObjectForDB, String> {
}
