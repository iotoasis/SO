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
    public ProfileForDB retrieveProfile(String profileId) {
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

    // retrieve one
    public ProfileForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveProfileById", id);
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
        super.sqlSession.insert("createAspect", model);
        return super.sqlSession.selectOne("retrieveProfileById", model.getId());
    }

    //  갱신 기능 구현
    public ProfileForDB update(ProfileForDB profileForDB) {
        //
        super.sqlSession.update("updateAspect", profileForDB);
        return super.sqlSession.selectOne("retrieveProfileByModel", profileForDB);
    }

    // 삭제 기능 구현
    public int delete(String id) {
        return super.sqlSession.delete("deleteProfile", id);
    }

}
