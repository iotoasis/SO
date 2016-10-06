package com.pineone.icbms.so.profile.store.mongo;

import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.profile.store.ProfileStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(ProfileStoreMongoImpl.class);

    //NOTE: PR 등록
    @Override
    public void createProfile(Profile profile) {
        logger.debug("Profile = " + profile.toString());
        ProfileDataObject profileDataObject = profileToDataObject(profile);
        profileRepository.save(profileDataObject);
    }

    //NOTE : PR List 조회
    @Override
    public List<Profile> retrieveProfileList() {
        List<ProfileDataObject> profileDataObjectList = profileRepository.findAll();
        List<Profile> profileList = new ArrayList<>();
        for(ProfileDataObject profileDataObject : profileDataObjectList){
            logger.debug("Profile = " + dataObjectToProfile(profileDataObject));
            profileList.add(dataObjectToProfile(profileDataObject));
        }
        return profileList;
    }

    //NOTE: PR 개별 조회
    @Override
    public Profile retrieveProfileDetail(String profileId) {
        ProfileDataObject profileDataObject = profileRepository.findOne(profileId);
        logger.debug("Profile = " + dataObjectToProfile(profileDataObject));
        return dataObjectToProfile(profileDataObject);
    }

    @Override
    public List<Profile> findByContextModelId(String contextModelId) {
        List<ProfileDataObject> profileDataObjectList = profileRepository.findByContextModelId(contextModelId);
        List<Profile> profileList = new ArrayList<>();
        for(ProfileDataObject profileDataObject : profileDataObjectList){
            logger.debug("Profile = " + dataObjectToProfile(profileDataObject));
            profileList.add(dataObjectToProfile(profileDataObject));
        }
        return profileList;
    }

    @Override
    public void addPriority(Profile profile, String priority){
        ProfileDataObject profileDataObject = profileRepository.findOne(profile.getId());
        profileDataObject.setPriority(priority);
        profileRepository.save(profileDataObject);
        logger.debug("profile : " + profileDataObject.toString());
    }

    private ProfileDataObject profileToDataObject(Profile profile) {
        if(profile == null) return null;
        return new ProfileDataObject(profile.getId(), profile.getName(), profile.getContextModelId(), profile.getServiceModelId(),
                profile.getBizContextName(), profile.getPeriod());
    }

    private Profile dataObjectToProfile(ProfileDataObject profileDataObject){
        if(profileDataObject == null) return null;
        return new Profile(profileDataObject.getId(), profileDataObject.getName(), profileDataObject.getContextModelId(),
                profileDataObject.getServiceModelId(), profileDataObject.getBizContextName(), profileDataObject.getPeriod());
    }
}
