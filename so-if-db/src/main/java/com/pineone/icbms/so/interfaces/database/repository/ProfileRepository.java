package com.pineone.icbms.so.interfaces.database.repository;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * ProfileForDB repository(DAO for table) interface.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 13..
 */
@Repository
@Table(name = "profile")
public interface ProfileRepository extends JpaRepository<ProfileForDB, String> {

    List<ProfileForDB> findByContextModelIdAndLocationUri(String contextModelId, String locationId);
    List<ProfileForDB> findByEnabled(boolean checker);
}
