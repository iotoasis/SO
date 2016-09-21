package com.pineone.icbms.so.scheduler.store.mongo;

import com.pineone.icbms.so.scheduler.entity.ScheduledProfile;
import com.pineone.icbms.so.scheduler.store.SchedulerStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 9. 6..
 */

@Repository
public class SchedulerStoreMongoImpl implements SchedulerStore {

    public static final Logger logger = LoggerFactory.getLogger(SchedulerStoreMongoImpl.class);

    @Autowired
    SchedulerRepository schedulerRepository;


    //NOTE : DB에 스케줄 프로파일 데이터 등록
    @Override
    public void createScheduledProfile(ScheduledProfile scheduledProfile) {
        logger.debug("ScheduledProfile = " + scheduledProfile.toString());
        ScheduledProfileDataObject scheduledProfileDataObject = scheduledProfileToDataObject(scheduledProfile);
        schedulerRepository.save(scheduledProfileDataObject);
    }

    //NOTE : 스케줄 목록 조회
    @Override
    public List<ScheduledProfile> retrieveScheduledProfile() {
        List<ScheduledProfileDataObject> scheduledProfileDataObjectList = schedulerRepository.findAll();
        List<ScheduledProfile> scheduledProfileList = new ArrayList<>();
        for(ScheduledProfileDataObject scheduledProfileDataObject : scheduledProfileDataObjectList){
            scheduledProfileList.add(dataObjectToSchedulerDataObject(scheduledProfileDataObject));
            logger.debug("ScheduledProfile = " + dataObjectToSchedulerDataObject(scheduledProfileDataObject).toString());
        }
        return scheduledProfileList;
    }

    //NOTE : 디비에 해당 프로파일 내용이 있는지 확인
    @Override
    public boolean isExistScheduledProfile(String profileId) {
        boolean checker = schedulerRepository.exists(profileId);
        logger.debug("ProfileId = " + profileId);
        return checker;
    }

    //NOTE : 스케쥴 상태로 목록 조회
    @Override
    public List<ScheduledProfile> retrieveScheduledProfileByStatus(int status) {
        List<ScheduledProfileDataObject> scheduledProfileDataObjectList = schedulerRepository.findByStatus(status);
        List<ScheduledProfile> scheduledProfileList = new ArrayList<>();
        for(ScheduledProfileDataObject scheduledProfileDataObject : scheduledProfileDataObjectList){
            scheduledProfileList.add(dataObjectToSchedulerDataObject(scheduledProfileDataObject));
        }
        return scheduledProfileList;
    }

    //NOTE : 스케줄 목록에 있는 상태 업데이트
    @Override
    public void updateStatus(ScheduledProfile scheduledProfile, int status) {
        ScheduledProfileDataObject scheduledProfileDataObject = schedulerRepository.findById(scheduledProfile.getId());
        scheduledProfileDataObject.setStatus(status);
        schedulerRepository.save(scheduledProfileDataObject);
        logger.debug("scheduledProfile : " + scheduledProfileDataObject.toString());
    }


    private ScheduledProfileDataObject scheduledProfileToDataObject(ScheduledProfile scheduledProfile) {
        if(scheduledProfile == null) return null;
        return new ScheduledProfileDataObject(scheduledProfile.getId(), scheduledProfile.getPeriod(), 0);
    }

    private ScheduledProfile dataObjectToSchedulerDataObject(ScheduledProfileDataObject schedulerDataObject){
        if(schedulerDataObject == null) return null;
        return new ScheduledProfile(schedulerDataObject.getId(), schedulerDataObject.getPeriod());
    }
}
