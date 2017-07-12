package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jonghee on 2017-06-13.
 */
@Component
public class ProfileDao extends AbstractDao {

    //
    public ProfileForDB retrieveProfile(String profileId) {
        return null;
    }

    // Profile 전체 조회
    public List<ProfileForDB> retrieveProfileList(ProfileForDB model) {
        return super.sqlSession.selectList("", model);
    }

    // Profile 생성 Id 없는 경우 기능
//    public ProfileForDB createProfileByExceptID(ProfileForDB profileForDB) {
//        return null;
//    }

    // Profile 갱신 기능
    public ProfileForDB updateProfile(String id, ProfileForDB profileForDB) {
        return null;
    }

    //  Profile 삭제 기능
    public ProfileForDB deleteProfile(String id) {
        return null;
    }

    //  SDA 와 연동하는 contextModel API 로 Profile 정보 조회
    public List<ProfileForDB> getProfileByContextModelAndLocation(String contextModelId, String locationUri) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("contextModelId", contextModelId);
        map.put("locationUri", locationUri);

        return super.sqlSession.selectList("getProfileByContextModelAndLocation", map);
    }

    public List<ProfileForDB> retrieveProfileListByEnable(boolean enabled) {
        return null;
    }

    //Profile 주기 업데이트
    public int updateProfilePeriod(ProfileForDB profileForDB) {
        return super.sqlSession.update("updateProfilePeriod", profileForDB);
    }

    //Profile Enabled 업데이트
    public int updateProfileEnabled(ProfileForDB profileForDB) {
        return super.sqlSession.update("updateProfileEnabled", profileForDB);
    }

    /*private ProfileForDB createProfileDataConversion(ProfileData profileData) {
        //

        ProfileForDB profileForDB = new ProfileForDB(null, profileData.getName(), profileData.getDescription(),
                profileData.getContextModelId(), profileData.getOrchestration_service_id(),
                profileData.getLocationId(), profileData.isEnabled(),  profileData.getPeriod());

        //JPA 객체 사용
//        ContextModelForDB contextModelForDB = contextModelDAO.retrieveContextModel(profileData.getContextModelId());
//        LocationForDB locationForDB = locationDAO.retrieveLocation(profileData.getLocationId());
//        OrchestrationServiceForDB orchestrationServiceForDB = orchestrationServiceDAO.retrieveOrchestrationService(
//                profileData.getOrchestration_service_id());
//
//        ProfileForDB profileForDB = new ProfileForDB(profileData.getName(), contextModelForDB,
//                orchestrationServiceForDB, locationForDB, true, profileData.getDescription(), profileData.getPeriod());
        return profileForDB;
    }*/

    /*private ProfileForDB updateProfileDataConversion(ProfileData profileData) {
        //
        ProfileForDB profileForDB = new ProfileForDB(null, profileData.getName(), profileData.getDescription(),
                profileData.getContextModelId(), profileData.getOrchestration_service_id(),
                profileData.getLocationId(), profileData.isEnabled(), profileData.getPeriod(),
                Calendar.getInstance().getTime());

        //JPA 객체 사용
//        ContextModelForDB contextModelForDB = contextModelDAO.retrieveContextModel(profileData.getContextModelId());
//        LocationForDB locationForDB = locationDAO.retrieveLocation(profileData.getLocationId());
//        OrchestrationServiceForDB orchestrationServiceForDB = orchestrationServiceDAO.retrieveOrchestrationService(
//                profileData.getOrchestration_service_id());
//
//        ProfileForDB profileForDB = new ProfileForDB(profileData.getName(), contextModelForDB,
//                orchestrationServiceForDB, locationForDB, true, profileData.getDescription(),
//                Calendar.getInstance().getTime(), profileData.getPeriod());
        return profileForDB;
    }*/
}
