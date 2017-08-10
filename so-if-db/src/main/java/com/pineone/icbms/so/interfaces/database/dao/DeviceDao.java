package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.DeviceForDB;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jonghee on 2017-06-22.
 */
@Component
public class DeviceDao extends AbstractDao {
    public List<DeviceForDB> retrieveDeviceList(String functionUri, String aspect, String locationUri) {
//        //
//        List<DeviceForDB> deviceForDBList = new ArrayList<>();
//        deviceForDBList = deviceRepository.findByFunctionIdAndAspectIdAndLocationId(functionId, aspectId, locationUri);
//        return deviceForDBList;

        //return super.sqlSession.selectList("retrieveDeviceList", map);

        String id = null;

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("functionId", functionUri);
        map.put("aspectId", aspect);
        map.put("locationId", locationUri);
        List<DeviceForDB> deviceForDBList = super.sqlSession.selectList("retrieveDeviceList", map);

        for (DeviceForDB deviceForDB : deviceForDBList) {
            // FunctionForDB function;
            deviceForDB.setFunction(
                    super.sqlSession.selectOne("retrieveFunctionFromDevice", functionUri)
            );
            // AspectForDB aspect;
            deviceForDB.setAspect(
                    super.sqlSession.selectOne("retrieveAspectFromDevice", aspect)
            );
            // LocationForDB location;
            //map.clear();
            //map.put("locationId", locationUri);
            deviceForDB.setLocation(
                    super.sqlSession.selectOne("retrieveLocationFromDevice", locationUri)
            );
        }

        return deviceForDBList;
    }

    public DeviceForDB create(DeviceForDB deviceForDB) {
        super.sqlSession.insert("createDevice", deviceForDB);
        return super.sqlSession.selectOne("retrieveDeviceById", deviceForDB.getId());
    }

    public List<DeviceForDB> retrieve() {
        return super.sqlSession.selectList("retrieveDeviceByModel");
    }

    public DeviceForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveDeviceById", id);
    }

    public int update(DeviceForDB model) {
        return super.sqlSession.update("updateDevice", model);
    }

    public int delete(String id) {
        return super.sqlSession.delete("deleteDevice", id);
    }
}
