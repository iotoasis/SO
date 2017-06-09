package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.ProfileData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IProfileDAO;
import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.interfaces.database.repository.ProfileRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by melvin on 2017. 3. 30..
 */
@Service
//  Profile Data Access 기능 구현
public class ProfileDAO implements IProfileDAO {

    @Autowired
    ProfileRepository profileRepository;

    // Profile 단일 조회
    @Override
    public ProfileForDB retrieveProfile(String id) {
        return profileRepository.findOne(id);
    }

    // Profile 전체 조회
    @Override
    public List<ProfileForDB> retrieveProfileList() {
        return profileRepository.findAll();
    }

    // Profile 생성 Id 없는 경우 기능
    @Override
    public ProfileForDB createProfileByExceptID(ProfileData profileData) {
        //
        ProfileForDB profileForDB = createProfileDataConversion(profileData);
        profileForDB.setId("PR-" + IdUtils.createRandomUUID());
//        ProfileTransFormData profileTransFormData = new ProfileTransFormData(profileForDB.getId(), profileForDB.getPeriod());
//        String sendData = DataConversion.objectToString(profileTransFormData);
//        ClientService clientService = new ClientService();
//        clientService.requestPostService("http://localhost:8080/so/scheduler", sendData);

        profileRepository.save(profileForDB);
        return profileForDB;
    }



    // Profile 갱신 기능
    @Override
    public ProfileForDB updateProfile(String id, ProfileData profileData) {
        //
        ProfileForDB profileForDB = profileRepository.findOne(id);
        String createDate = profileForDB.getCreated_date();
        if (profileForDB != null) {
            profileForDB = updateProfileDataConversion(profileData);
            profileForDB.setId(id);
            profileForDB.setCreated_date(createDate);
            profileRepository.save(profileForDB);
        } else {
            profileForDB = createProfileByExceptID(profileData);
        }
        return profileForDB;
    }

    //  Profile 삭제 기능
    @Override
    public String deleteProfile(String id) {
        ProfileForDB ProfileForDB = profileRepository.findOne(id);
        profileRepository.delete(id);
        String message = "Delete  " + ProfileForDB;
        return message;
    }

    //  SDA 와 연동하는 contextModel API 로 Profile 정보 조회
    @Override
    public List<ProfileForDB> getProfileByContextModelAndLocation(String contextModelId, String locationUri) {
        List<ProfileForDB> profileForDBList = profileRepository.
                findByContextModelIdAndLocationUri(contextModelId, locationUri);
        return profileForDBList;
    }

    @Override
    public List<ProfileForDB> retrieveProfileListByEnable(boolean checker) {
        List<ProfileForDB> profileForDBList = profileRepository.findByEnabled(checker);
        return profileForDBList;
    }

    //Profile 주기 업데이트
    @Override
    public ProfileForDB updateProfilePeriod(String profileId, int period) {
        ProfileForDB profileForDB = profileRepository.findOne(profileId);
        profileForDB.setPeriod(period);
        profileRepository.save(profileForDB);
        return profileForDB;
    }

    //Profile Enabled 업데이트
    @Override
    public ProfileForDB updateProfileEnabled(String profileId, boolean b) {
        //
        ProfileForDB profileForDB = profileRepository.findOne(profileId);
        profileForDB.setEnabled(b);
        profileRepository.save(profileForDB);
        return profileForDB;
    }

    private ProfileForDB createProfileDataConversion(ProfileData profileData) {
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
    }

    private ProfileForDB updateProfileDataConversion(ProfileData profileData) {
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
    }
}
