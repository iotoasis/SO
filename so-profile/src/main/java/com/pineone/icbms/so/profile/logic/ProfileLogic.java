package com.pineone.icbms.so.profile.logic;

import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.servicemodel.entity.ServiceModel;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 11..
 */
public interface ProfileLogic {
    //
    List<String> retrieveServiceModelNameList();
    List<String> retrieveContextModelNameList();
    List<String> retrieveBizContextNameList();
    String registerProfile(Profile profile);
    List<String> retrieveProfileNameList();
    Profile retrieveProfileDetail(String profileId);
    List<String> retrieveProfileIdList();
    String executeScheduleProfile(String profileId);
    void extractContextModelQueueData();
    List<Profile> retrieveProfileList();
}
