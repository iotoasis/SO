package com.pineone.icbms.so.profile.store.mongo;

import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.profile.store.ProfileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 23..
 * NOTE: Mongo DB를 - profile 정보 연결
 */
@Repository
public class ProfileStoreMongoImpl implements ProfileStore {

    @Autowired
    ProfileRepository profileRepository;

    //NOTE: PR 등록
    @Override
    public void createProfile(Profile profile) {
        ProfileDataObject profileDataObject = profileToDataObject(profile);
        profileRepository.save(profileDataObject);
    }

    //NOTE : PR List 조회
    @Override
    public List<Profile> retrieveProfileList() {
        List<ProfileDataObject> profileDataObjectList = profileRepository.findAll();
        List<Profile> profileList = new ArrayList<>();
        for(ProfileDataObject profileDataObject : profileDataObjectList){
            profileList.add(dataObjectToProfile(profileDataObject));
        }
        return profileList;
    }

    //NOTE: PR 개별 조회
    @Override
    public Profile retrieveProfileDetail(String profileId) {
        ProfileDataObject profileDataObject = profileRepository.findOne(profileId);
        return dataObjectToProfile(profileDataObject);
    }

    private ProfileDataObject profileToDataObject(Profile profile) {
        if(profile == null) return null;
        return new ProfileDataObject(profile.getId(), profile.getName(), profile.getContextModelName(), profile.getServiceModelName(),
                profile.getBizContextName(), profile.getPeriod());
    }

    private Profile dataObjectToProfile(ProfileDataObject profileDataObject){
        if(profileDataObject == null) return null;
        return new Profile(profileDataObject.getId(), profileDataObject.getName(), profileDataObject.getContextModelName(),
                profileDataObject.getServiceModelName(), profileDataObject.getBizContextName(), profileDataObject.getPeriod());
    }
}
