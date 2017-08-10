package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.FunctionForDB;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class FunctionDao extends AbstractDao {
    //
    public FunctionForDB create(FunctionForDB functionForDB) {
        super.sqlSession.insert("", functionForDB);
        return super.sqlSession.selectOne("createFunction", functionForDB.getId());
    }

    public FunctionForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveFunctionById", id);
    }

    public List<FunctionForDB> retrieve(FunctionForDB functionForDB) {
        return super.sqlSession.selectList("retrieveFunctionByModel", functionForDB);
    }

    public List<FunctionForDB> retrieve() {
        return super.sqlSession.selectList("retrieveFunctionByModel");
    }

    public int update(FunctionForDB functionForDB) {
        return super.sqlSession.update("updateFunction", functionForDB);
    }

    public int delete(String id) {
        return super.sqlSession.delete("deleteFunction", id);
    }
}
