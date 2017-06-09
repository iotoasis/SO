package com.pineone.icbms.so.interfaces.database.model;

import com.pineone.icbms.so.util.time.DateFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * ProfileForDB model for authoring.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 13..
 */
@Entity
@Table(name = "profile")
public class ProfileForDB extends CommonEntity {

    @Id
    @Column(name = "id")
    private String id;

//    @Column(name = "name")
//    private String name;

    @Column(name = "context_model_id")
    private String contextModelId;

    @Column(name = "orchestration_service_id")
    private String orchestrationServiceId;

    @Column(name = "location_uri")
    private String locationUri;


    //JPA 활용 객체 전체를 수신시 사용

//    //  Join 필요
//    @OneToOne
//    @JoinColumn(name = "context_model_id")
//    private ContextModelForDB contextModel;
//
//    //  Join 필요
//    @OneToOne
//    @JoinColumn(name = "orchestration_service_id")
//    private OrchestrationServiceForDB orchestrationService;
//
//    //  Join 필요
//    @OneToOne
//    @JoinColumn(name = "location_id")
//    private LocationForDB location;


//    @Column(name= "description")
//    String description;

    @Column
    int period;

    @Column
    boolean enabled;

//    @Temporal(TemporalType.DATE)
//    Date create_date;

//    @Temporal(TemporalType.DATE)
//    Date modified_date;


    //    @JoinColumn(name = "id")
//    ContextModelForDB contextModel;
//
//    @JoinColumn(name = "id")
//    OrchestrationServiceForDB orchestrationService;
//
//    @Column(name = "location_id")
//    String locationId;

//    public ContextModelForDB getContextModel() {
//        return contextModel;
//    }
//
//    public void setContextModel(ContextModelForDB contextModel) {
//        this.contextModel = contextModel;
//    }
//
//    public OrchestrationServiceForDB getOrchestrationService() {
//        return orchestrationService;
//    }
//
//    public void setOrchestrationService(OrchestrationServiceForDB orchestrationService) {
//        this.orchestrationService = orchestrationService;
//    }
//
//    public String getLocationId() {
//        return locationId;
//    }
//
//    public void setLocationId(String locationId) {
//        this.locationId = locationId;
//    }


    public ProfileForDB() {
        Date date = Calendar.getInstance().getTime();
        String dateString = DateFormat.dateFormat(date);
        this.created_date = dateString;
        this.modified_date = dateString;
    }

    public ProfileForDB(String id) {
        this();
        this.id = id;
    }

    public ProfileForDB(String id, String name) {
        this(id);
        this.name = name;

    }

    public ProfileForDB(String id, String name, String description) {
        this(id, name);
        this.description = description;

    }

    public ProfileForDB(String id, String name, String description, String contextModelId) {
        this(id, name, description);
        this.contextModelId = contextModelId;
    }

    public ProfileForDB(String id, String name, String description, String contextModelId,
                        String orchestrationServiceId) {
        this(id, name, description, contextModelId);
        this.orchestrationServiceId = orchestrationServiceId;
    }

    public ProfileForDB(String id, String name, String description, String contextModelId,
                        String orchestrationServiceId, String locationUri) {
        this(id, name, description, contextModelId, orchestrationServiceId);
        this.locationUri = locationUri;
    }

    public ProfileForDB(String id, String name, String description, String contextModelId,
                        String orchestrationServiceId, String locationUri, boolean enabled) {
        this(id, name, description, contextModelId, orchestrationServiceId, locationUri);
        this.enabled = enabled;
    }

    public ProfileForDB(String id, String name, String description, String contextModelId,
                        String orchestrationServiceId, String locationUri, boolean enabled, int period) {
        this(id, name, description, contextModelId, orchestrationServiceId, locationUri, enabled);
        this.period = period;
    }

    public ProfileForDB(String id, String name, String description, String contextModelId,
                        String orchestrationServiceId, String locationUri, boolean enabled, int period,
                        Date modified_date) {
        this(id, name, description, contextModelId, orchestrationServiceId, locationUri, enabled, period);
        this.modified_date = DateFormat.dateFormat(modified_date);
    }


    //JPA 활용 객체 전체를 수신시 사용
//    public ProfileForDB(String name, ContextModelForDB contextModelForDB, OrchestrationServiceForDB orchestrationServiceForDB,
//                        LocationForDB locationForDB, boolean enabled, String description, int period) {
//        //
//        this.name = name;
//        this.contextModel = contextModelForDB;
//        this.orchestrationService = orchestrationServiceForDB;
//        this.location = locationForDB;
//        this.enabled = enabled;
//        this.description = description;
//        Date date = Calendar.getInstance().getTime();
//        String dateString = DateFormat.dateFormat(date);
//        this.created_date = dateString;
//        this.modified_date = dateString;
//        this.period = period;
//    }
//
//    public ProfileForDB(String name, ContextModelForDB contextModelForDB, OrchestrationServiceForDB orchestrationServiceForDB,
//                        LocationForDB locationForDB, boolean enabled, String description, Date modified_date, int period) {
//        //
//        this.name = name;
//        this.contextModel = contextModelForDB;
//        this.orchestrationService = orchestrationServiceForDB;
//        this.location = locationForDB;
//        this.enabled = enabled;
//        this.description = description;
//        this.modified_date = DateFormat.dateFormat(modified_date);
//        this.period = period;
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

    public String getContextModelId() {
        return contextModelId;
    }

    public void setContextModelId(String contextModelId) {
        this.contextModelId = contextModelId;
    }

    public String getOrchestrationServiceId() {
        return orchestrationServiceId;
    }

    public void setOrchestrationServiceId(String orchestrationServiceId) {
        this.orchestrationServiceId = orchestrationServiceId;
    }

    public String getLocationUri() {
        return locationUri;
    }

    public void setLocationUri(String locationId) {
        this.locationUri = locationId;
    }

    //JPA 활용 객체 전체를 수신시 사용
    //    public ContextModelForDB getContextModel() {
//        return contextModel;
//    }
//
//    public void setContextModel(ContextModelForDB contextModel) {
//        this.contextModel = contextModel;
//    }
//
//    public OrchestrationServiceForDB getOrchestrationService() {
//        return orchestrationService;
//    }
//
//    public void setOrchestrationService(OrchestrationServiceForDB orchestrationServiceForDB) {
//        this.orchestrationService = orchestrationServiceForDB;
//    }
//
//    public LocationForDB getLocation() {
//        return location;
//    }
//
//    public void setLocation(LocationForDB location) {
//        this.location = location;
//    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "ProfileForDB{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", contextModelId=" + contextModelId +
                ", orchestrationServiceId=" + orchestrationServiceId +
                ", created_date='" + created_date + '\'' +
                ", modified_date='" + modified_date + '\'' +
                ", locationId =" + locationUri +
                ", enabled=" + enabled +
                '}';
    }
}
