package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.LocationForDB;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class LocationDao extends AbstractDao {
    //
    // retrieve one
    public LocationForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveLocationById", id);
    }

    // retrieve list
    public List<LocationForDB> retrieve(LocationForDB model) {
        return super.sqlSession.selectList("retrieveLocationByModel", model);
    }

    // retrieve all
    public List<LocationForDB> retrieve() {
        return super.sqlSession.selectList("retrieveLocationByModel");
    }

    // 저장 기능 구현
    public LocationForDB create(LocationForDB model) {
        super.sqlSession.insert("createLocation", model);
        return super.sqlSession.selectOne("retrieveLocationById", model.getId());
    }

    // 갱신 기능 구현
    public int update(LocationForDB model) {
        //
        return super.sqlSession.update("updateLocation", model);
    }

    // 삭제 기능 구현
    public int delete(String id) {
        return super.sqlSession.delete("deleteLocation", id);
    }

}
