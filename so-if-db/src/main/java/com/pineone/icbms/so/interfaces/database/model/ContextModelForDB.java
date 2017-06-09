package com.pineone.icbms.so.interfaces.database.model;

import com.pineone.icbms.so.util.time.DateFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Context Model model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
@Entity
@Table(name = "context_model")
public class ContextModelForDB extends CommonEntity {

    @Id
    @Column(name = "id")
    private String id;

//    @OneToMany
//    @JoinTable(name = "CM_CI",
//            joinColumns = @JoinColumn(name = "context_model_id"),
//            inverseJoinColumns = @JoinColumn(name = "context_information_id"))
//    private List<ContextInformationForDB> contextInformationForDBList;

//    @
//    private List<String> contextInformationForDBList;


    @Transient
    private List<ContextInformationForDB> contextInformationForDBList;

    public ContextModelForDB() {
        Date date = Calendar.getInstance().getTime();
        String dateString = DateFormat.dateFormat(date);
        this.created_date = dateString;
        this.modified_date = dateString;
    }

    public ContextModelForDB(String id) {
        this();
        this.id = id;
    }

    public ContextModelForDB(String id, String name) {
        this(id);
        this.name = name;
    }

    public ContextModelForDB(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    public ContextModelForDB(String id, String name, String description, Date modified_date) {
        this(id, name, description);
        this.modified_date = DateFormat.dateFormat(modified_date);
    }

//    public ContextModelForDB(String id, String name, List<String> contextInformationIdList, String description) {
//        this(id,name,description);
//        this.contextInformationForDBList = contextInformationIdList;
//    }

//    @Column(name = "name")
//    private String name;

//    @Column(name = "context_information_ids")
//    private List<String> context_information_ids;

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

//    public List<String> getContext_information_ids() {
//        return context_information_ids;
//    }
//
//    public void setContext_information_ids(List<String> context_information_ids) {
//        this.context_information_ids = context_information_ids;
//    }


    public List<ContextInformationForDB> getContextInformationForDBList() {
        return contextInformationForDBList;
    }

    public void setContextInformationForDBList(List<ContextInformationForDB> contextInformationForDBList) {
        this.contextInformationForDBList = contextInformationForDBList;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

//    public List<ContextInformationForDB> getContextInformationForDBList() {
//        return contextInformationForDBList;
//    }
//
//    public void setContextInformationForDBList(List<ContextInformationForDB> contextInformationForDBList) {
//        this.contextInformationForDBList = contextInformationForDBList;
//    }

    //    public List<ContextInformationForDB> getContextInformationIdList() {
//        return contextInformationForDBList;
//    }
//
//    public void setContextInformationIdList(List<ContextInformationForDB> contextInformationForDBList) {
//        this.contextInformationForDBList = contextInformationForDBList;
//    }


    //    @ManyToMany
//    List<ContextInformationForDB> contextInformationList;
//
//    public List<ContextInformationForDB> getContextInformationList() {
//        return contextInformationList;
//    }
//
//    public void setContextInformationList(List<ContextInformationForDB> contextInformationList) {
//        this.contextInformationList = contextInformationList;
//    }


    @Override
    public String toString() {
        return "ContextModelForDB{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created_date='" + created_date + '\'' +
                ", modified_date='" + modified_date + '\'' +
                ", contextInformationForDBList=" + contextInformationForDBList +
                '}';
    }
}
