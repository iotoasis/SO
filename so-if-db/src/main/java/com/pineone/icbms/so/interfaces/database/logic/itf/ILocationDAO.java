package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.LocationData;
import com.pineone.icbms.so.interfaces.database.model.LocationForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
public interface ILocationDAO {
    //
    LocationForDB retrieveLocation(String id);

    List<LocationForDB> retrieveLocationList();

    LocationForDB createLocation(LocationData locationData);

    LocationForDB updateLocation(String id, LocationData AspectData);

    String deleteLocation(String id);

    //  URI 로 Location 내용 조회
    LocationForDB retrieveLocationByUri(String uri);
}
