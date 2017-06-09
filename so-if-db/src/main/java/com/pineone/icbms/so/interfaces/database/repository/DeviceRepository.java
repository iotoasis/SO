package com.pineone.icbms.so.interfaces.database.repository;

import com.pineone.icbms.so.interfaces.database.model.DeviceForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by melvin on 2017. 4. 21..
 */

@Repository
@Table(name = "device")
public interface DeviceRepository extends JpaRepository<DeviceForDB, String> {

    List<DeviceForDB> findByFunctionalityIdAndAspectIdAndLocationId(String functionalityId,
                                                                    String aspectId, String locationId);

}
