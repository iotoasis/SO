package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.FixedDeviceForDB;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class FixedDeviceDao extends AbstractDao {
    //
//    public FixedDeviceForDB retrieveFixedDevice(String id) {
//        return null;
//    }

    // retrieve one
    public FixedDeviceForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveFixedDeviceById", id);
    }

    // retrieve list
    public List<FixedDeviceForDB> retrieve(FixedDeviceForDB model) {
        return super.sqlSession.selectList("retrieveFixedDeviceByModel", model);
    }

    // retrieve all
    public List<FixedDeviceForDB> retrieve() {
        return super.sqlSession.selectList("retrieveFixedDeviceByModel");
    }

    // 저장 기능 구현
    public FixedDeviceForDB create(FixedDeviceForDB model) {
        super.sqlSession.insert("createFixedDevice", model);
        return super.sqlSession.selectOne("retrieveFixedDeviceById", model.getFixedDeviceId());
    }

    // 갱신 기능 구현
    public int update(FixedDeviceForDB model) {
        //
        return super.sqlSession.update("updateFixedDevice", model);
    }

    // 삭제 기능 구현
    public int delete(String id) {
        return super.sqlSession.delete("deleteFixedDevice", id);
    }
}
