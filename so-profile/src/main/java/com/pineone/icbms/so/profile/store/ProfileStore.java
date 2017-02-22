package com.pineone.icbms.so.profile.store;

import com.pineone.icbms.so.profile.entity.Profile;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 11..
 * NOTE : Profile 저장소의 공통기능 정의
 */
public interface ProfileStore {
    //
    void createProfile(Profile profile);
    List<Profile> retrieveProfileList();
    Profile retrieveProfileDetail(String profileId);
    List<Profile> findByContextModelId(String contextModelId);
    void addPriority(Profile profile, String priority);
    Profile retrieveProfileDetailByName(String profileName);
}
