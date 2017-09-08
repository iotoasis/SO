package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonghee on 2017-06-02.
 * grib session 처리 데이터 모델
 */
//@Data
@JsonPropertyOrder({"id", "createDate"})
@ToString
public class SessionEntity implements Serializable {

    @Getter @Setter
    private String id;                     // tracking session id UUID
    
    @Getter @Setter
    @JsonProperty("createDate")
    private String createDate;

    @Getter @Setter
    @JsonProperty("PRIORITY_KEY")
    private String priorityKey;            //": "HIGH"

    @Getter @Setter
    @JsonProperty("PROFILE_NAME")
    private String profileName;            //": "발표도우미"

    @Getter @Setter
    @JsonProperty("CONTEXTMODEL_NAME")
    private String contextmodelName;       //": "TEST"

    @Getter @Setter
    @JsonProperty("CONTEXTMODEL_KEY")
    private String contextmodelKey;        //": "cm-announcement-on"

    @Getter @Setter
    @JsonProperty("LOCATION_ID")
    private String locationId;             //": "["http://www.iotoasis.org/ontology/t1eng_605"]"

    @Getter @Setter
    @JsonProperty("CONTEXTMODEL_RESULT")
    private String contextmodelResult;     //": "Happen"

    @Getter @Setter
    @JsonProperty("PROFILE_KEY")
    private String profileKey;             //": "pr-announcement-on"

    @Getter @Setter
    @JsonProperty("SERVICEMODEL_KEY")
    private String servicemodelKey;        //": "sm-announcement-on"

    @Getter @Setter
    @JsonProperty("SERVICEMODEL_NAME")
    private String servicemodelName;       //": "강의실 스마트 발표 시작 서비스모델"

    @Getter @Setter
    @JsonProperty("SERVICEMODEL_RESULT")
    private String servicemodelResult;     //": "CONTROL_EXECUTION", CONTROL_ERROR, CONTROL_IGNORE

    @Getter @Setter
    @JsonProperty("SERVICE_KEY")
    private String serviceKey;              //": "["si-classroom-light-off","si-classroom-blind-on","si-classroom-beamscreen-on"]"

    @Getter @Setter
    @JsonProperty("VIRTUALOBJECT_KEY")
    private String virtualobjectKey;        //": "["vo-smartlight01-001-power-control","vo-smartlight01-002-power-control","vo-smartlight02-001-power-control","vo-smartlight02-002-power-control","vo-smartlight03-001-power-control","vo-smartlight03-002-power-control","vo-smartlight04-001-power-control","vo-smartlight04-002-power-control","vo-blind01-001-open-control","vo-blind01-002-open-control","vo-beamscreen01-001-open-control"]"

    @Getter @Setter
    @JsonProperty("VIRTUALOBJECT_RESULT")
    private String virtualobjectResult;     //": "CONTROL_EXECUTION"

    @Getter @Setter
    @JsonProperty("DEVICE_KEY")
    private String deviceKey;               //": "["/herit-in/herit-cse/ONDB_SmartLight01_001","/herit-in/herit-cse/ONDB_SmartLight01_002","/herit-in/herit-cse/ONDB_SmartLight02_001","/herit-in/herit-cse/ONDB_SmartLight02_002","/herit-in/herit-cse/ONDB_SmartLight03_001","/herit-in/herit-cse/ONDB_SmartLight03_002","/herit-in/herit-cse/ONDB_SmartLight04_001","/herit-in/herit-cse/ONDB_SmartLight04_002","/herit-in/herit-cse/ONDB_Blind01_001","/herit-in/herit-cse/ONDB_Blind01_002","/herit-in/herit-cse/ONDB_BeamScreen01_001"]"

    @Getter @Setter
    @JsonProperty("DEVICE_LOCATION")
    private String deviceLocation;          //": "["T1ENG_605","T1ENG_605","T1ENG_605","T1ENG_605","T1ENG_605","T1ENG_605","T1ENG_605","T1ENG_605","T1ENG_605","T1ENG_605","T1ENG_605"]"

    @Getter @Setter
    @JsonProperty("DEVICE_RESULT")
    private String deviceResult;            //": "CONTROL_EXECUTION"

    @Getter @Setter
    @JsonProperty("SERVICE_RESULT")
    private String serviceResult;           //": "CONTROL_EXECUTION"

}
