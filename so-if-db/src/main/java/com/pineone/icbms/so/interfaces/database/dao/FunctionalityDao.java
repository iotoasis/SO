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
    public FunctionalityForDB retrieveFunctionality(String id) {
        return null;
    }

    public List<FunctionalityForDB> retrieveFunctionalityList() {
        return null;
    }

//    public FunctionalityForDB createFunctionality(FunctionalityData functionalityData) {
//        return null;
//    }
//
//    public FunctionalityForDB updateFunctionality(String id, FunctionalityData functionalityData) {
//        return null;
//    }

    public String deleteFunctionality(String id) {
        return null;
    }

//    public FunctionalityForDB findOne(String id) {
//        return null;
//    }
//    public List<FunctionalityForDB> findAll() {
//        return null;
//    }
    public FunctionalityForDB create(FunctionalityForDB functionalityForDB) {
        super.sqlSession.insert("", functionalityForDB);
        return super.sqlSession.selectOne("createFunctionality", functionalityForDB.getId());
    }

    public FunctionalityForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveFunctionalityById", id);
    }

    public List<FunctionalityForDB> retrieve(FunctionalityForDB functionalityForDB) {
        return super.sqlSession.selectList("retrieveFunctionalityByModel", functionalityForDB);
    }

    public int update(FunctionalityForDB functionalityForDB) {
        return super.sqlSession.update("updateFunctionality", functionalityForDB);
    }

    public int delete(String id) {
        return super.sqlSession.delete("deleteFunctionality", id);
    }
}
