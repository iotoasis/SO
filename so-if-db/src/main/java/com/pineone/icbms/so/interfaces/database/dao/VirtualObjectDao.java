package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jonghee on 2017. 3. 27..
 */
@Component
public class VirtualObjectDao extends AbstractDao {
    //
    public VirtualObjectForDB retrieveVirtualObject(String id) {
        return sqlSession.selectOne("retrieveVirtualObjectById", id);
    }

    public List<VirtualObjectForDB> retrieveVirtualObject(List<VirtualObjectForDB> virtualObjectForDBList) {
        return sqlSession.selectList("retrieveVirtualObject", virtualObjectForDBList);
    }

    public List<VirtualObjectForDB> retrieveVirtualObjectList(VirtualObjectForDB virtualObjectForDB) {
        return sqlSession.selectList("retrieveVirtualObjectByModel", virtualObjectForDB);
    }

//    public VirtualObjectForDB createVirtualObject(VirtualObjectData virtualObjectData) {
//        return null;
//    }
//
//    public VirtualObjectForDB updateVirtualObject(String id, VirtualObjectData virtualObjectData) {
//        return null;
//    }

    public String deleteVirtualObject(String id) {
        return null;
    }

    public List<VirtualObjectForDB> getVirtualObjectListByOrchestrationId(String orchestrationServiceId) {
        return super.sqlSession.selectList("getVirtualObjectListByOrchestrationId", orchestrationServiceId);
    }

    public List<VirtualObjectForDB> retrieveVirtualObjectListByCompositeVirtualObjectId(String compositeVirtualObjectId) {
        return super.sqlSession.selectList("retrieveVirtualObjectListByCompositeVirtualObjectId", compositeVirtualObjectId);
    }

    // retrieve one
    public VirtualObjectForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveVirtualObjectById", id);
    }

    // retrieve list
    public List<VirtualObjectForDB> retrieve(VirtualObjectForDB model) {
        return super.sqlSession.selectList("retrieveVirtualObjectByModel", model);
    }

    // retrieve all
    public List<VirtualObjectForDB> retrieve() {
        return super.sqlSession.selectList("retrieveVirtualObjectByModel");
    }

    // Aspect 저장 기능 구현
    public VirtualObjectForDB create(VirtualObjectForDB model) {
        String sessionId = IdUtils.createRandomUUID();
        model.setId("VO-" + sessionId);
        super.sqlSession.insert("createVirtualObject", model);
        return super.sqlSession.selectOne("retrieveVirtualObjectById", model.getId());
    }

    //  Aspect 갱신 기능 구현
    public VirtualObjectForDB update(VirtualObjectForDB model) {
        //
        super.sqlSession.update("updateVirtualObject", model);
        return super.sqlSession.selectOne("retrieveVirtualObjectByModel", model);
    }

    // Aspect 삭제 기능 구현
    public int delete(String id) {
        return super.sqlSession.delete("deleteVirtualObject", id);
    }
}
