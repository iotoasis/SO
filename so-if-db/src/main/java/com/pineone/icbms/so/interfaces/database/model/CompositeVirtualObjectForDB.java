package com.pineone.icbms.so.interfaces.database.model;

import com.pineone.icbms.so.util.time.DateFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Composite Virtual Object model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
@Entity
@Table(name = "composite_virtual_object")
public class CompositeVirtualObjectForDB extends CommonEntity {

    @Id
    @Column(name = "id")
    private String id;

//    @Column(name = "name")
//    private String name;

    //  JOIN 필요
    @JoinColumn(table = "functionality", name = "id")
    private String functionality_id;

    //  JOIN 필요
    @JoinColumn(table = "aspect", name = "id")
    private String aspect_id;

    //
//    @OneToMany
//    @JoinTable(name = "CVO_VO",
//            joinColumns = @JoinColumn(name = "composite_virtual_object_id"),
//            inverseJoinColumns = @JoinColumn(name = "virtual_object_id"))
//    @Column(name = "virtualObjectForDBList")
//    @JoinColumn(name = "virtual_object_id")

    @Transient
    private List<VirtualObjectForDB> virtualObjectForDBList;

    @JoinColumn(name = "type")
    private String type;

    public CompositeVirtualObjectForDB() {
        Date date = Calendar.getInstance().getTime();
        String dateString = DateFormat.dateFormat(date);
        this.created_date = dateString;
        this.modified_date = dateString;
    }

    public CompositeVirtualObjectForDB(String id) {
        this();
        this.id = id;
    }

    public CompositeVirtualObjectForDB(String id, String name) {
        this(id);
        this.name = name;
    }

    public CompositeVirtualObjectForDB(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    public CompositeVirtualObjectForDB(String id, String name, String description, String functionality, String aspect) {
        this(id, name, description);
        this.functionality_id = functionality;
        this.aspect_id = aspect;
    }

    public CompositeVirtualObjectForDB(String id, String name, String description, String functionality,  String aspect,
                                       String type) {
        this(id, name, description, functionality, aspect);
        this.type = type;
    }

    public CompositeVirtualObjectForDB(String id, String name, String description, String functionality, String aspect,
                                       String type, Date modified_date) {
        this(id, name, description, functionality, aspect, type);
        this.modified_date = DateFormat.dateFormat(modified_date);
    }

//    public CompositeVirtualObjectForDB(String id, String name, String description, String functionality, String aspect,
//                                       String type, Date modified_date,
//                                       List<VirtualObjectForDB> virtualObjectForDBList) {
//        this(id, name, description, functionality, aspect, type, modified_date);
////        this.virtualObjectForDBList = virtualObjectForDBList;
//    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public String getFunctionality_id() {
        return functionality_id;
    }

    public void setFunctionality_id(String functionality_id) {
        this.functionality_id = functionality_id;
    }

    public String getAspect_id() {
        return aspect_id;
    }

    public void setAspect_id(String aspect_id) {
        this.aspect_id = aspect_id;
    }

    public List<VirtualObjectForDB> getVirtualObjectForDBList() {
        return virtualObjectForDBList;
    }

    public void setVirtualObjectForDBList(List<VirtualObjectForDB> virtualObjectForDBList) {
        this.virtualObjectForDBList = virtualObjectForDBList;
    }

    //    public List<VirtualObjectForDB> getVirtualObjectForDBList() {
//        return virtualObjectForDBList;
//    }
//
//    public void setVirtualObjectForDBList(List<VirtualObjectForDB> virtualObjectForDBList) {
//        this.virtualObjectForDBList = virtualObjectForDBList;
//    }
//
//    public void addVirtualObject(VirtualObjectForDB virtualObjectForDB) {
//        if (virtualObjectForDBList == null) {
//            virtualObjectForDBList = new ArrayList<VirtualObjectForDB>();
//        }
//        virtualObjectForDBList.add(virtualObjectForDB);
//    }

    @Override
    public String toString() {
        return "CompositeVirtualObjectForDB{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", functionality_id='" + functionality_id + '\'' +
                ", aspect_id='" + aspect_id + '\'' +
                ", created_date='" + created_date + '\'' +
                ", modified_date='" + modified_date + '\'' +
                ", virtualObjectForDBList=" + virtualObjectForDBList +
                ", type='" + type + '\'' +
                '}';
    }


    //    public List<String> getVirtualObjectForDBList() {
//        return virtualObjectForDBList;
//    }
//
//    public void setVirtualObjectForDBList(List<String> virtualObjectForDBList) {
//        this.virtualObjectForDBList = virtualObjectForDBList;
//    }

}
