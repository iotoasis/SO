package com.pineone.icbms.so.interfaces.database.repository;

import com.pineone.icbms.so.interfaces.database.model.ContextModelForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * ContextModelForDB repository(DAO for table) interface.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 13..
 */
@Repository
@Table(name = "context_model")
public interface ContextModelRepository extends JpaRepository<ContextModelForDB, String> {
//    ContextModelForDB findByContextModelId(String contextMoDelId);
}
