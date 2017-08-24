package com.pineone.icbms.so.util.conversion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by melvin on 2017. 5. 26..
 */
@ToString
public class ProfileTransFormData {

    @Getter @Setter
    private String simulatorType;
    @Getter @Setter
    private String id;
    @Getter @Setter
    int period;

    public ProfileTransFormData(String id) {
        this.id = id;
    }

    public ProfileTransFormData() {
    }

//    public String getProfileId() {
//        return profileId;
//    }
//
//    public void setProfileId(String profileId) {
//        this.profileId = profileId;
//    }
//
//    public String getSimulatorType() {
//        return simulatorType;
//    }
//
//    public void setSimulatorType(String simulatorType) {
//        this.simulatorType = simulatorType;
//    }
//
//    @Override
//    public String toString() {
//        return "ProfileTransFormData{" +
//                "id='" + profileId + '\'' +
//                '}';
//    }
}
