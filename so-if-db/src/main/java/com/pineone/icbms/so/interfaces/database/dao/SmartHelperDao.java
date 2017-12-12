package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.SmartHelperForDB;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by melvin on 2017. 12. 11.
 */
@Component
public class SmartHelperDao extends AbstractDao {
    //
    // retrieve one
    public SmartHelperForDB retrieveById(String id) {
        return super.sqlSession.selectOne("retrieveById", id);
    }

    // retrieve one
    public List<SmartHelperForDB> retrieveByParam(SmartHelperForDB model) {
        return super.sqlSession.selectList("retrieveByName", model);
    }
    
    // retrieve all
    public List<SmartHelperForDB> retrieve() {
        return super.sqlSession.selectList("retrieveAll");
    }

    // 저장 기능 구현
    public SmartHelperForDB create(SmartHelperForDB model) {
        super.sqlSession.insert("createSmartHelper", model);
        return retrieveById(model.getId());
    }

    // 갱신 기능 구현
    public int update(SmartHelperForDB model) {
        return super.sqlSession.update("updateSmartHelper", model);
    }

    // 삭제 기능 구현
    public int delete(String id) {
        return super.sqlSession.delete("deleteSmartHelper", id);
    }

}
