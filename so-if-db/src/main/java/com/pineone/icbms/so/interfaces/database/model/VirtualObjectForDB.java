package com.pineone.icbms.so.interfaces.database.model;

import com.pineone.icbms.so.util.time.DateFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Virtual Object model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
//@NamedNativeQueries({
//        @NamedNativeQuery(
//                name = "findRecentVirtualObject",
//                query = "SELECT * FROM virtual_object ORDER BY created_date DESC LIMIT 1",
//                resultClass = VirtualObjectForDB.class
//        )
//})
@Entity
@Table(name = "virtual_object")
public class VirtualObjectForDB extends CommonEntity {

    @Id
    @Column(name = "id")
    private String id;

//    @Column(name = "name")
//    private String name;

    // JPA 객체 사용시
//    //  JOIN 필요
//    @OneToOne
//    @JoinColumn(name = "functionality_id")
//    private FunctionalityForDB functionalityId;
//
//
//    //  JOIN 필요
//
//    @OneToOne
//    @JoinColumn(name = "aspect_id")
//    private AspectForDB aspectId;


    @JoinColumn(name = "functionality_id")
    private String functionalityId;

    @JoinColumn(name = "aspect_id")
    private String aspectId;

    public VirtualObjectForDB() {
        Date date = Calendar.getInstance().getTime();
        String dateString = DateFormat.dateFormat(date);
        this.created_date = dateString;
        this.modified_date = dateString;
    }

    public VirtualObjectForDB(String id) {
        this();
        this.id = id;
    }

    public VirtualObjectForDB(String id, String name) {
        this(id);
        this.name = name;
    }

    public VirtualObjectForDB(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    public VirtualObjectForDB(String id, String name, String description, String functionalityId, String aspectId) {
        this(id, name, description);
        this.functionalityId = functionalityId;
        this.aspectId = aspectId;
    }

    public VirtualObjectForDB(String id, String name, String description, String functionalityId, String aspectId, Date modified_date) {
        this(id, name, description, functionalityId, aspectId);
        this.modified_date = DateFormat.dateFormat(modified_date);
    }

//    public VirtualObjectForDB(String name, FunctionalityForDB functionalityId, AspectForDB aspectId, String description) {
//
//        this.name = name;
//        this.functionalityId = functionalityId;
//        this.aspectId = aspectId;
//        this.description = description;
//        Date date = Calendar.getInstance().getTime();
//        String dateString = DateFormat.dateFormat(date);
//        this.created_date = dateString;
//        this.modified_date = dateString;
//    }


//    public VirtualObjectForDB(String name, FunctionalityForDB functionalityId, AspectForDB aspectId, String description, Date modified_date) {
//        this.name = name;
//        this.functionalityId = functionalityId;
//        this.aspectId = aspectId;
//        this.description = description;
//        this.modified_date = DateFormat.dateFormat(modified_date);
//    }

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

    public String getFunctionalityId() {
        return functionalityId;
    }

    public void setFunctionalityId(String functionalityId) {
        this.functionalityId = functionalityId;
    }

    public String getAspectId() {
        return aspectId;
    }

    public void setAspectId(String aspectId) {
        this.aspectId = aspectId;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "VirtualObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", functionality_id=" + functionalityId +
                ", aspect_id=" + aspectId +
                ", description='" + description + '\'' +
                ", create_date=" + created_date +
                ", modified_date=" + modified_date +
                '}';
    }
}
