package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class CompositeVirtualObjectDao extends AbstractDao {
    //
    public CompositeVirtualObjectForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveCompositeVirtualObjectById", id);
    }

    public List<CompositeVirtualObjectForDB> retrieve(CompositeVirtualObjectForDB model) {
        return super.sqlSession.selectList("retrieveCompositeVirtualObjectByModel", model);
    }

//    public CompositeVirtualObjectForDB createCompositeVirtualObject(CompositeVirtualObjectData compositeVirtualObjectData) {
//        return null;
//    }
//
//    public CompositeVirtualObjectForDB updateCompositeVirtualObject(String id, CompositeVirtualObjectData compositeVirtualObjectData) {
//        return null;
//    }

//    public int deleteCompositeVirtualObject(String id) {
//        return super.sqlSession.delete("deleteCompositeVirtualObject", id);
//    }

//    public CompositeVirtualObjectForDB findOne(String id) {
//        return null;
//    }
//    public List<CompositeVirtualObjectForDB>findAll() {
//        return null;
//    }
    public int delete(String id) {
        return super.sqlSession.delete("deleteCompositeVirtualObject", id);
    }

    // os 와 연결된 cvo list
    public List<CompositeVirtualObjectForDB> getCompositeVirtualObjectListByOrchestrationId(String orchestrationServiceId) {
        return super.sqlSession.selectList("getCompositeVirtualObjectListByOrchestrationId", orchestrationServiceId);
    }

}
