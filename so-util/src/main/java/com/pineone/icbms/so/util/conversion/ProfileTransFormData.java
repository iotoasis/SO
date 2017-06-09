package com.pineone.icbms.so.util.conversion;

/**
 * Created by melvin on 2017. 5. 26..
 */
public class ProfileTransFormData {

    private String simulatorType;

    private String profileId;

    public ProfileTransFormData(String id) {
        this.profileId = id;
    }

    public ProfileTransFormData() {
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getSimulatorType() {
        return simulatorType;
    }

    public void setSimulatorType(String simulatorType) {
        this.simulatorType = simulatorType;
    }

    @Override
    public String toString() {
        return "ProfileTransFormData{" +
                "id='" + profileId + '\'' +
                '}';
    }
}
