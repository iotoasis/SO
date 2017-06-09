package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.LocationData;
import com.pineone.icbms.so.interfaces.database.logic.itf.ILocationDAO;
import com.pineone.icbms.so.interfaces.database.model.LocationForDB;
import com.pineone.icbms.so.interfaces.database.repository.LocationRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by melvin on 2017. 3. 30..
 */
@Service
public class LocationDAO implements ILocationDAO {

    @Autowired
    LocationRepository locationRepository;

    // Location 단일 조회
    @Override
    public LocationForDB retrieveLocation(String id) {
        return locationRepository.findOne(id);
    }

    // Location 전체 조회
    @Override
    public List<LocationForDB> retrieveLocationList() {
        return locationRepository.findAll();
    }

    // Location 생성 기능
    @Override
    public LocationForDB createLocation(LocationData locationData) {
        //
        LocationForDB locationForDB = createLocationDataConversion(locationData);
        locationForDB.setId("LC" + IdUtils.createRandomUUID());
        locationRepository.save(locationForDB);
        return locationForDB;
    }

    // Location 갱신 기능
    @Override
    public LocationForDB updateLocation(String id, LocationData locationData) {
        //
        LocationForDB locationForDB = locationRepository.findOne(id);
        String createDate = locationForDB.getCreated_date();
        if (locationForDB != null) {
            locationForDB = updateLocationDataConversion(locationData);
            locationForDB.setId(id);
            locationForDB.setCreated_date(createDate);
            locationRepository.save(locationForDB);
        } else {
            locationForDB = createLocation(locationData);
        }
        return locationForDB;
    }

    //  Location 삭제 기능
    @Override
    public String deleteLocation(String id) {
        LocationForDB LocationForDB = locationRepository.findOne(id);
        locationRepository.delete(id);
        String message = "Delete  " + LocationForDB;
        return message;
    }

    // URI 로 로케이션 정보 조회
    @Override
    public LocationForDB retrieveLocationByUri(String uri) {
        return locationRepository.findByUri(uri);
    }

    private LocationForDB createLocationDataConversion(LocationData locationData) {
        //
        LocationForDB locationForDB = new LocationForDB(null, locationData.getName(),
                locationData.getDescription(), locationData.getUri());
        return locationForDB;
    }

    private LocationForDB updateLocationDataConversion(LocationData locationData) {
        //
        LocationForDB locationForDB = new LocationForDB(null, locationData.getName(),
                locationData.getDescription(), locationData.getUri(), Calendar.getInstance().getTime());
        return locationForDB;
    }
}
