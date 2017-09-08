package com.pineone.icbms.so.util.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionData {
    @Getter @Setter
    //@JsonProperty("priority_key")
    private String PRIORITY_KEY;        //: HIGH
    @Getter @Setter
    private String PROFILE_NAME;        //: 발표도우미
    @Getter @Setter
    private String CONTEXTMODEL_NAME;   //: test
    @Getter @Setter
    private String CONTEXTMODEL_KEY;    //: cm-announcement-on
    @Getter @Setter
    private List<String> LOCATION_ID;   //: [http;//://www.iotoasis.org/ontology/t1eng_605]
    @Getter @Setter
    private String CONTEXTMODEL_RESULT; //: happen
    @Getter @Setter
    private String PROFILE_KYE;         //: pr-announcement-on
    @Getter @Setter
    private String SERVICEMODEL_KEY;    //: sm-announcement-on
    @Getter @Setter
    private String SERVICEMODEL_NAME;   //: 강의실 스마트 발표 시작 서비스모델
    @Getter @Setter
    private String SERVICEMODEL_RESULT; //: control_execution
    @Getter @Setter
    private List<String> SERVICE_KEY;   //: [si-classroom-light-off,si-classroom-blind-on,si-classroom-beamscreen-on]
    @Getter @Setter
    private List<String> VIRTUALOBJECT_KEY; //: [vo-smartlight01-001-power-control,vo-smartlight01-002-power-control,vo-smartlight02-001-power-control,vo-smartlight02-002-power-control,vo-smartlight03-001-power-control,vo-smartlight03-002-power-control,vo-smartlight04-001-power-control,vo-smartlight04-002-power-control,vo-blind01-001-open-control,vo-blind01-002-open-control,vo-beamscreen01-001-open-control]
    @Getter @Setter
    private String VIRTUALOBJECT_RESULT;    //: control_execution
    @Getter @Setter
    private List<String> DEVICE_KEY;        //: [/herit-in/herit-cse/ondb_smartlight01_001,/herit-in/herit-cse/ondb_smartlight01_002,/herit-in/herit-cse/ondb_smartlight02_001,/herit-in/herit-cse/ondb_smartlight02_002,/herit-in/herit-cse/ondb_smartlight03_001,/herit-in/herit-cse/ondb_smartlight03_002,/herit-in/herit-cse/ondb_smartlight04_001,/herit-in/herit-cse/ondb_smartlight04_002,/herit-in/herit-cse/ondb_blind01_001,/herit-in/herit-cse/ondb_blind01_002,/herit-in/herit-cse/ondb_beamscreen01_001]
    @Getter @Setter
    private List<String> DEVICE_LOCATION;   //: [t1eng_605,t1eng_605,t1eng_605,t1eng_605,t1eng_605,t1eng_605,t1eng_605,t1eng_605,t1eng_605,t1eng_605,t1eng_605]
    @Getter @Setter
    private String DEVICE_RESULT;       //: control_execution
    @Getter @Setter
    private String SERVICE_RESULT;      //: control_execution

}
