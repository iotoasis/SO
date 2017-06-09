package com.pineone.icbms.so.interfaces.database.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * handler entity model.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
@MappedSuperclass
public class CommonEntity { //extends AbstractPersistable<String> {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    int id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description")
    String description;

//    @Column(name = "createdDate", nullable = false)
//    Timestamp createdDate;
//
//    @Column(name = "modifiedDate", nullable = false)
//    Timestamp modifiedDate;

    @Column(name = "created_date")
    String created_date;

    @Column(name = "modified_date")
    String modified_date;

//    @Column(name = "simulation_id")
//    String simulationId;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

//    public String getSimulationId() {
//        return simulationId;
//    }
//
//    public void setSimulationId(String simulationId) {
//        this.simulationId = simulationId;
//    }
}
