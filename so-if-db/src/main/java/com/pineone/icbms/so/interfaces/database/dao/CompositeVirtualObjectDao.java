package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.util.id.IdUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class CompositeVirtualObjectDao extends AbstractDao {
    //
    // os 와 연결된 cvo list
    public List<CompositeVirtualObjectForDB> retrieveCompositeVirtualObjectListByOrchestrationId(String orchestrationServiceId) {
        return super.sqlSession.selectList("retrieveCompositeVirtualObjectListByOrchestrationId", orchestrationServiceId);
    }


    // retrieve one
    public CompositeVirtualObjectForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveCompositeVirtualObjectById", id);
    }

    // retrieve list
    public List<CompositeVirtualObjectForDB> retrieve(CompositeVirtualObjectForDB model) {
        return super.sqlSession.selectList("retrieveCompositeVirtualObjectByModel", model);
    }

    // retrieve all
    public List<CompositeVirtualObjectForDB> retrieve() {
        return super.sqlSession.selectList("retrieveCompositeVirtualObjectByModel");
    }

    // 저장 기능 구현
    public CompositeVirtualObjectForDB create(CompositeVirtualObjectForDB model) {
        String sessionId = IdUtils.createRandomUUID();
        model.setId("CVO-" + sessionId);
        super.sqlSession.insert("createCompositeVirtualObject", model);
        return super.sqlSession.selectOne("retrieveCompositeVirtualObjectById", model.getId());
    }

    // 갱신 기능 구현
    public CompositeVirtualObjectForDB update(CompositeVirtualObjectForDB model) {
        //
        super.sqlSession.update("updateCompositeVirtualObject", model);
        return super.sqlSession.selectOne("retrieveCompositeVirtualObjectByModel", model);
    }

    // 삭제 기능 구현
    public int delete(String id) {
        return super.sqlSession.delete("deleteCompositeVirtualObject", id);
    }

}
