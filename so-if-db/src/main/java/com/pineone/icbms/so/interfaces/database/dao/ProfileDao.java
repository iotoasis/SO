package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.util.id.IdUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jonghee on 2017-06-13.
 */
@Component
public class ProfileDao extends AbstractDao {
    //
    public List<ProfileForDB> retrieveProfileByContextModel(String contextModelId) {
        return super.sqlSession.selectList("retrieveProfileByContextModel", contextModelId);
    }

    public List<ProfileForDB> retrieveProfileByLocation(String locationUri) {
        return super.sqlSession.selectList("retrieveProfileByLocation", locationUri);
    }
	
	public ProfileForDB retrieveProfile(String profileId) {
        //return null;
        return super.sqlSession.selectOne("retrieveProfile", profileId);
    }

    //  SDA 와 연동하는 contextModel API 로 Profile 정보 조회
    public List<ProfileForDB> getProfileByContextModelAndLocationUri(String contextModelId, String locationUri) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("contextModelId", contextModelId);
        map.put("locationUri", locationUri);

        return super.sqlSession.selectList("getProfileByContextModelAndLocationUri", map);
    }

    public List<ProfileForDB> retrieveProfileListByEnable(boolean enabled) {
        return super.sqlSession.selectList("retrieveProfileListByEnable", enabled?1:0);
    }

    //Profile 주기 업데이트
    public int updateProfilePeriod(ProfileForDB profileForDB) {
        return super.sqlSession.update("updateProfilePeriod", profileForDB);
    }

    //Profile Enabled 업데이트
    public int updateProfileEnabled(ProfileForDB profileForDB) {
        return super.sqlSession.update("updateProfileEnabled", profileForDB);
    }

    //Profile 전체 Enabled 업데이트
    public int updateProfileEnabledAll(ProfileForDB profileForDB) {
        return super.sqlSession.update("updateProfileEnabledAll", profileForDB);
    }

    // retrieve one
    public ProfileForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveProfile", id);
    }

    // retrieve list
    public List<ProfileForDB> retrieve(ProfileForDB profileForDB) {
        return super.sqlSession.selectList("retrieveProfileByModel", profileForDB);
    }

    // retrieve all
    public List<ProfileForDB> retrieve() {
        return super.sqlSession.selectList("retrieveProfileByModel");
    }

    // 저장 기능 구현
    public ProfileForDB create(ProfileForDB model) {
        String sessionId = IdUtils.createRandomUUID();
        model.setId("PR-" + sessionId);
        super.sqlSession.insert("createProfile", model);
        return super.sqlSession.selectOne("retrieveServiceByProfile", model.getId());
    }

    //  갱신 기능 구현
    public ProfileForDB update(ProfileForDB profileForDB) {
        //
        super.sqlSession.update("updateProfile", profileForDB);
        return super.sqlSession.selectOne("retrieveProfileByModel", profileForDB);
    }

    // 삭제 기능 구현
    public int delete(String id) {
        return super.sqlSession.delete("deleteProfile", id);
    }

}
