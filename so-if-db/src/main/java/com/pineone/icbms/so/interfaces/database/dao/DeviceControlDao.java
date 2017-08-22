package com.pineone.icbms.so.interfaces.database.dao;
import com.pineone.icbms.so.interfaces.database.model.DeviceControlForDB;
import org.springframework.stereotype.Component;
//import com.pineone.icbms.so.interfaces.database.model.key.DeviceControlPK;

import java.util.HashMap;
import java.util.List;

/**
 * Created by melvin on 2017. 5. 15..
 */
@Component
public class DeviceControlDao extends AbstractDao {
    //
    public DeviceControlForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveDeviceControlById", id);
    }

    public List<DeviceControlForDB> retrieve(DeviceControlForDB model) {
        return super.sqlSession.selectList("retrieveDeviceControlByModel", model);
    }

    public List<DeviceControlForDB> retrieve() {
        return super.sqlSession.selectList("retrieveDeviceControlByModel");
    }

    // 저장 기능 구현
    public DeviceControlForDB create(DeviceControlForDB model) {
        super.sqlSession.insert("createAspect", model);
        return super.sqlSession.selectOne("retrieveAspectById", model.getId());
    }

    public int update(DeviceControlForDB model) {
        return super.sqlSession.update("updateDeviceControl", model);
    }

    public int delete(String id) {
        return super.sqlSession.delete("deleteDeviceControl", id);
    }

    public DeviceControlForDB retrieveDeviceControlByDeviceIdAndContextModelId(String deviceId, String contextModelId) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", deviceId);
        map.put("contextModelId", contextModelId);

        return super.sqlSession.selectOne("retrieveDeviceControlByDeviceIdAndContextModelId", map);
    }

}
