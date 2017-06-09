package com.pineone.icbms.so.interfaces.database.repository;

import com.pineone.icbms.so.interfaces.database.model.FunctionalityForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * FunctionalityForDB repository(DAO for table) interface.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 13..
 */
@Repository
@Table(name = "functionality")
public interface FunctionalityRepository extends JpaRepository<FunctionalityForDB, String> {
}
