package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.LocationForDB;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class LocationDao extends AbstractDao {
    //
    public LocationForDB retrieveLocation(String id) {
        return null;
    }

    public List<LocationForDB> retrieveLocationList() {
        return null;
    }

//    public LocationForDB createLocation(LocationData locationData) {
//        return null;
//    }
//
//    public LocationForDB updateLocation(String id, LocationData AspectData) {
//        return null;
//    }

    public String deleteLocation(String id) {
        return null;
    }

    //  URI 로 Location 내용 조회
    public LocationForDB retrieveLocationByUri(String uri) {
        return null;
    }
}
