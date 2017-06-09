package com.pineone.icbms.so.interfaces.database.repository;

import com.pineone.icbms.so.interfaces.database.model.DeviceControlCallbackForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * DeviceControlCallbackForDB repository(DAO for table) interface.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 13..
 */
@Repository
@Table(name = "device_control_callback")
public interface DeviceControlCallbackRepository extends JpaRepository<DeviceControlCallbackForDB, String> {
}
