package com.pineone.icbms.so.interfaces.database.repository;

import com.pineone.icbms.so.interfaces.database.model.AspectForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Aspect repository(DAO for table) interface.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 13..
 */
@Repository
@Table(name = "aspect")
public interface AspectRepository extends JpaRepository<AspectForDB, String> {
}
