package com.pineone.icbms.so.interfaces.database.model;

import javax.persistence.*;

/**
 * fixed_device model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 16..
 */
@Entity
@Table(name = "fixed_device")
public class FixedDeviceForDB extends CommonEntity {

    @Id
    @Column(name = "fixed_device_id")
    private String id;

//    @Column(name = "name")
//    private String name;

    // Join 필요
    @JoinColumn(table = "profile", name = "id")
    private String profile_id;

    @Column(name = "device_uri")
    String device_uri;

    //  Join 필요
    @JoinColumn(table = "virtual_object", name = "id")
    private String virtual_object_id;

//    @Column(name= "description")
//    String description;

//    @Temporal(TemporalType.DATE)
//    Date create_date;

//    @Temporal(TemporalType.DATE)
//    Date modified_date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getDevice_uri() {
        return device_uri;
    }

    public void setDevice_uri(String device_uri) {
        this.device_uri = device_uri;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public String getVirtual_object_id() {
        return virtual_object_id;
    }

    public void setVirtual_object_id(String virtual_object_id) {
        this.virtual_object_id = virtual_object_id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    //    @Column(name = "profile_id")
//    String profileId;
//
//    @Column(name = "virtual_object_id")
//    String virtualObjectId;
//
//    public String getProfileId() {
//        return profileId;
//    }
//
//    public void setProfileId(String profileId) {
//        this.profileId = profileId;
//    }
//
//    public String getVirtualObjectId() {
//        return virtualObjectId;
//    }
//
//    public void setVirtualObjectId(String virtualObjectId) {
//        this.virtualObjectId = virtualObjectId;
//    }

    @Override
    public String toString() {
        return "FixedDeviceForDB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profile_id=" + profile_id +
                ", description='" + description + '\'' +
                ", device_uri='" + device_uri + '\'' +
                ", virtual_object_id=" + virtual_object_id +
                ", created_date='" + created_date + '\'' +
                ", modified_date='" + modified_date + '\'' +
                '}';
    }
}
