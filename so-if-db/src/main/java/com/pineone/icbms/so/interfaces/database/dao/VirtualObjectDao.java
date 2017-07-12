package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 27..
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
}
