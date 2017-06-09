package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.ProfileData;
import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
public interface IProfileDAO {
    //
    ProfileForDB retrieveProfile(String id);

    List<ProfileForDB> retrieveProfileList();

    ProfileForDB createProfileByExceptID(ProfileData profileData);

    ProfileForDB updateProfile(String id, ProfileData profileData);

    String deleteProfile(String id);

    //      SDA 와 연동하는 contextModel 과 Location 정보로 Profile 정보 조회
    List<ProfileForDB> getProfileByContextModelAndLocation(String contextModelSid, String uri);

    List<ProfileForDB> retrieveProfileListByEnable(boolean checker);

    ProfileForDB updateProfilePeriod(String profileId, int period);

    ProfileForDB updateProfileEnabled(String id, boolean b);
}
