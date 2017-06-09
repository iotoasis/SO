package com.pineone.icbms.so.interfaces.database.model;

import com.pineone.icbms.so.util.time.DateFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by melvin on 2017. 4. 21..
 */

@Entity
@Table(name = "device")
public class DeviceForDB extends CommonEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "device_uri")
    private String deviceUri;

    @OneToOne
    @JoinColumn(name = "functionality_id")
    private FunctionalityForDB functionality;

    @OneToOne
    @JoinColumn(name = "aspect_id")
    private AspectForDB aspect;

    @OneToOne
    @JoinColumn(name = "location_id")
    private LocationForDB location;

    public DeviceForDB() {
        Date date = Calendar.getInstance().getTime();
        String dateString = DateFormat.dateFormat(date);
        this.created_date = dateString;
        this.modified_date = dateString;
    }

    public DeviceForDB(String id) {
        this();
        this.id = id;
    }

    public DeviceForDB(String id, String name) {
        this(id);
        this.name = name;

    }
    public DeviceForDB(String id, String name, String description){
        this(id, name);
        this.description = description;
    }

    public DeviceForDB(String id,String name, String description, String deviceUri){
        this(id, name, description);
        this.deviceUri = deviceUri;
    }

    public DeviceForDB(String id, String name, String description, String deviceUri,
                       FunctionalityForDB functionalityForDB, AspectForDB aspectForDB){
        this(id, name, description, deviceUri);
        this.functionality = functionalityForDB;
        this.aspect = aspectForDB;
    }

    public DeviceForDB(String id, String name,  String description, String deviceUri,
                       FunctionalityForDB functionalityForDB, AspectForDB aspectForDB, LocationForDB locationForDB){
        this(id, name, description, deviceUri, functionalityForDB, aspectForDB);
        this.location = locationForDB;
    }

    public DeviceForDB(String id, String name,  String description , String deviceUri,
                       FunctionalityForDB functionalityForDB, AspectForDB aspectForDB, LocationForDB locationForDB,
                       Date modified_date){
        this(id, name, description, deviceUri, functionalityForDB, aspectForDB, locationForDB);
        this.modified_date = DateFormat.dateFormat(modified_date);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceUri() {
        return deviceUri;
    }

    public void setDeviceUri(String deviceUri) {
        this.deviceUri = deviceUri;
    }

    public FunctionalityForDB getFunctionality() {
        return functionality;
    }

    public void setFunctionality(FunctionalityForDB functionalityId) {
        this.functionality = functionalityId;
    }

    public AspectForDB getAspect() {
        return aspect;
    }

    public void setAspect(AspectForDB aspectId) {
        this.aspect = aspectId;
    }

    public LocationForDB getLocation() {
        return location;
    }

    public void setLocation(LocationForDB locationId) {
        this.location = locationId;
    }

    @Override
    public String toString() {
        return "DeviceForDB{" +
                "id='" + id + '\'' +
                ", deviceUri='" + deviceUri + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", functionality=" + functionality +
                ", aspect=" + aspect +
                ", location=" + location +
                ", created_date='" + created_date + '\'' +
                ", modified_date='" + modified_date + '\'' +
                '}';
    }
}
