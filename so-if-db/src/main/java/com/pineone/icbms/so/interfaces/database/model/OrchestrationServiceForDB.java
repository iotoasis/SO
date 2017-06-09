package com.pineone.icbms.so.interfaces.database.model;

import com.pineone.icbms.so.interfaces.database.model.mapper.OS_VO_MapperForDB;
import com.pineone.icbms.so.util.time.DateFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Orchestration Service model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
@Entity
@Table(name = "orchestration_service")
public class OrchestrationServiceForDB extends CommonEntity {

    @Id
    @Column(name = "id")
    private String id;

//    @Column(name = "name")
//    private String name;


//    @OneToMany
//    @JoinTable(name = "OS_VO",
//            joinColumns = @JoinColumn(name = "orchestration_service_id"),
//            inverseJoinColumns = @JoinColumn(name = "virtual_object_id"))
////    @Column(name = "virtual_object_ids")
////    @JoinColumn(name = "virtual_object_id")
//    private List<VirtualObjectForDB> virtual_object_ids;
//
////    @Column(name = "virtual_object_ids")
////    @JoinColumn(name = "virtual_object_id")
////    private List<String> virtual_object_ids;
//

//    @OneToMany
//    @JoinTable(name = "os_vo",
//            joinColumns = @JoinColumn(name = "orchestration_service_id"),
//            inverseJoinColumns = @JoinColumn(name = "virtual_object_id"))
//    private List<OS_VO_MapperForDB> os_vo_mapperForDBList;


    @Column(name = "parent_id")
    private String parent_id;

    @Transient
    private List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList;

    @Transient
    private List<VirtualObjectForDB> virtualObjectForDBList;


    public OrchestrationServiceForDB() {
        Date date = Calendar.getInstance().getTime();
        String dateString = DateFormat.dateFormat(date);
        this.created_date = dateString;
        this.modified_date = dateString;
    }

    public OrchestrationServiceForDB(String id) {
        this();
        this.id = id;
    }
    public OrchestrationServiceForDB(String id, String name) {
        this(id);
        this.name = name;
    }
    public OrchestrationServiceForDB(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }
    public OrchestrationServiceForDB(String id, String name, String description, String parent_id) {
        this(id, name, description);
        this.parent_id = parent_id;
    }

    public OrchestrationServiceForDB(String id, String name, String description, String parent_id, Date modified_date) {
        this(id, name, description, parent_id);
        this.modified_date = DateFormat.dateFormat(modified_date);
    }

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

//    public List<String> getVirtualObject_ids() {
//        return virtualObject_ids;
//    }
//
//    public void setVirtualObject_ids(List<String> virtualObject_ids) {
//        this.virtualObject_ids = virtualObject_ids;
//    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

//    public List<VirtualObjectForDB> getVirtualObjectForDBList() {
//        return virtual_object_ids;
//    }
//
//    public void setVirtualObjectForDBList(List<VirtualObjectForDB> virtual_object_ids) {
//        this.virtual_object_ids = virtual_object_ids;
//    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

//    public List<OS_VO_MapperForDB> getOs_vo_mapperForDBList() {
//        return os_vo_mapperForDBList;
//    }
//
//    public void setOs_vo_mapperForDBList(List<OS_VO_MapperForDB> os_vo_mapperForDBList) {
//        this.os_vo_mapperForDBList = os_vo_mapperForDBList;
//    }

    //    public List<VirtualObjectForDB> getVirtualObjectForDBList() {
//        return virtual_object_ids;
//    }
//
//    public void setVirtualObjectForDBList(List<VirtualObjectForDB> virtual_object_ids) {
//        this.virtual_object_ids = virtual_object_ids;
//    }


    public List<CompositeVirtualObjectForDB> getCompositeVirtualObjectForDBList() {
        return compositeVirtualObjectForDBList;
    }

    public void setCompositeVirtualObjectForDBList(List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList) {
        this.compositeVirtualObjectForDBList = compositeVirtualObjectForDBList;
    }

    public List<VirtualObjectForDB> getVirtualObjectForDBList() {
        return virtualObjectForDBList;
    }

    public void setVirtualObjectForDBList(List<VirtualObjectForDB> virtualObjectForDBList) {
        this.virtualObjectForDBList = virtualObjectForDBList;
    }

    @Override
    public String toString() {
        return "OrchestrationServiceForDB{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", created_date='" + created_date + '\'' +
                ", modified_date='" + modified_date + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", compositeVirtualObjectForDBList=" + compositeVirtualObjectForDBList +
                ", virtualObjectForDBList=" + virtualObjectForDBList +
                '}';
    }
}

