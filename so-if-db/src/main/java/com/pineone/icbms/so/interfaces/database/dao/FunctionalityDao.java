package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.FunctionalityForDB;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class FunctionalityDao extends AbstractDao {
    //
    public FunctionalityForDB create(FunctionalityForDB functionForDB) {
    	super.sqlSession.insert("createFunction", functionForDB);
        return super.sqlSession.selectOne("retrieveFunctionById", functionForDB.getId());
    }

    public FunctionalityForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveFunctionById", id);
    }

    public List<FunctionalityForDB> retrieve(FunctionalityForDB functionForDB) {
        return super.sqlSession.selectList("retrieveFunctionByModel", functionForDB);
    }

    public List<FunctionalityForDB> retrieve() {
        return super.sqlSession.selectList("retrieveFunctionByModel");
    }

    public int update(FunctionalityForDB functionForDB) {
        return super.sqlSession.update("updateFunction", functionForDB);
    }

    public int delete(String id) {
        return super.sqlSession.delete("deleteFunction", id);
    }
}
