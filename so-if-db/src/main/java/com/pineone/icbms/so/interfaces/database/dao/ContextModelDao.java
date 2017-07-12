package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.ContextModelForDB;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class ContextModelDao extends AbstractDao {

    //
    public ContextModelForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveContextModelById", id);
    }

    public List<ContextModelForDB> retrieve(ContextModelForDB model) {
        return super.sqlSession.selectOne("retrieveContextModelByModel", model);
    }

    public ContextModelForDB create(ContextModelForDB model) {
        super.sqlSession.insert("createContextModel", model);
        return super.sqlSession.selectOne("retrieveContextModelById", model.getId());
    }

    public int update(String id, ContextModelForDB model) {
        return super.sqlSession.update("updateContextModel", model);
    }

    public int delete(String id) {
        return super.sqlSession.delete("deleteContextModel", id);
    }

    // SDA 와 연동하는 데이터 정보를 통해 CM 조회
//    ContextModelForDB retrieveContextModelByContextModelAPI(String context_model_api);
}
