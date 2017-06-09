package com.pineone.icbms.so.interfaces.database.repository;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by jonghee on 2017-06-02.
 */
@Repository
@Table(name = "tracking")
public interface TrackingRepository extends JpaRepository<TrackingEntity, String> {
}
