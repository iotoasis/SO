package com.pineone.icbms.so.interfaces.database.repository;

import com.pineone.icbms.so.interfaces.database.model.DeviceControlForDB;
import com.pineone.icbms.so.interfaces.database.model.DeviceForDB;
import com.pineone.icbms.so.interfaces.database.model.key.DeviceControlPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by melvin on 2017. 5. 15..
 */

@Repository
@Table(name = "device_control")
public interface DeviceControlRepository extends JpaRepository<DeviceControlForDB, DeviceControlPK> {
    //
    DeviceControlForDB findByIdAndContextModelId(String deviceId, String contextModelId);
}
