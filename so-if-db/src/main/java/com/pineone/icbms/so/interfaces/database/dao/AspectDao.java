package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.AspectForDB;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.util.id.IdUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * Created by melvin on 2017. 3. 28..
 */
@Component
public class AspectDao extends AbstractDao {
    
    // retrieve one
    public AspectForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveAspectById", id);
    }

    // retrieve list
    public List<AspectForDB> retrieve(AspectForDB model) {
        return super.sqlSession.selectList("retrieveAspectByModel", model);
    }

    // retrieve all
    public List<AspectForDB> retrieve() {
        return super.sqlSession.selectList("retrieveAspectByModel");
    }

    // 저장 기능 구현
    public AspectForDB create(AspectForDB model) {
        super.sqlSession.insert("createAspect", model);
        return super.sqlSession.selectOne("retrieveAspectById", model.getId());
    }

    // 갱신 기능 구현
    public int update(AspectForDB model) {
        //
        return super.sqlSession.update("updateAspect", model);
    }

    // 삭제 기능 구현
    public int delete(String id) {
        return super.sqlSession.delete("deleteAspect", id);
    }

    // virtualObject 에 포함되어 있는 Aspect 조회
    public AspectForDB retrieveAspectByVirtualObjectId(String virtualObjectId) {
        return super.sqlSession.selectOne("retrieveAspectByVirtualObjectId", virtualObjectId);
    }

}