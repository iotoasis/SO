package com.pineone.icbms.so.profile.store.map;

import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.profile.store.ProfileStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by melvin on 2016. 8. 11..
 * NOTE: Profile 저장 방식중에 Map 에 저장소 사용
 */


public class ProfileMapStore implements ProfileStore {

    private static ProfileMapStore instance;
    private ProfileMapStore(){};

    //NOTE : 이름으로 프로파일 조회
    private Map<String, Profile> profileStore = new HashMap<>();

    //NOTE: DB 에 Profile 데이터 생성
    @Override
    public void createProfile(Profile profile) {
        //
        profileStore.put(profile.getName(), profile);
    }

    //NOTE : DB 에서 Profile List 조회
    @Override
    public List<Profile> retrieveProfileList() {
        //
        List<Profile> profilesList = new ArrayList<>();
        for(String key : profileStore.keySet()){
            profilesList.add(profileStore.get(key));
        }
        return profilesList;
    }

    //NOTE : DB 에서 Profile 상세 조회
    @Override
    public Profile retrieveProfileDetail(String profileName) {
        Profile profile = profileStore.get(profileName);
        return profile;
    }

    @Override
    public List<Profile> findByContextModelId(String contextModelId) {
        return null;
    }

    public static ProfileMapStore getInstance(){
        if(instance == null)
            instance = new ProfileMapStore();
        return instance;
    }
}
