package com.pineone.icbms.so.interfaces.database.repository;

import com.pineone.icbms.so.interfaces.database.model.LocationForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by melvin on 2017. 3. 27..
 */

@Repository
@Table(name = "location")
public interface LocationRepository extends JpaRepository<LocationForDB, String> {
    LocationForDB findByUri(String uri);
}
