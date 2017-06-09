package com.pineone.icbms.so.interfaces.database;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.DeviceData;
import com.pineone.icbms.so.interfaces.database.controller.inputdata.ProfileData;
import com.pineone.icbms.so.interfaces.database.logic.DeviceDAO;
import com.pineone.icbms.so.interfaces.database.model.DeviceForDB;
import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Created by melvin on 2017. 4. 21..
 */

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE) //Jpa embaded DB 로 대체하는것을 금지 , 사용자 설정 디비로 테스트 환경 구성
//@TransactionConfiguration(defaultRollback=false) // Test 환경에서도 DB 에 기록할수 있도록 설정 / 롤백 금지
public class Device_CRUD_Test {

    @Autowired
    DeviceDAO deviceDAO;

    @Test
    public void r() throws Exception {
        //
//        DeviceForDB deviceForDB = deviceDAO.retrieveDevice("ONDB_Blind01_001");
//        System.out.println(deviceForDB);
    }

    @Test
    public void rList() throws Exception {
        //
        List<DeviceForDB> deviceForDBList = deviceDAO.retrieveDeviceList();
        System.out.println(deviceForDBList);
    }

    @Test
    public void c() throws Exception {
        //
        DeviceData deviceData = new DeviceData("testDev", "/herit-in/herit-cse/ONDB_Blind01_050", "Switch", "Electronicpower", "http://www.iotoasis.org/ontology/t1eng_605","테스트디바이스");

        DeviceForDB deviceForDB = deviceDAO.createDevice(deviceData);
        System.out.println(deviceForDB.toString());
    }

    @Test
    public void u() throws Exception {
        //
//        DeviceData deviceData = new DeviceData("newTest", "/herit-in/herit-cse/ONDB_Blind01_050", "Switch", "Electronicpower", "http://www.iotoasis.org/ontology/t1eng_605","new 테스트디바이스");
//        DeviceForDB deviceForDB = deviceDAO.updateDevice("DV544da750-cf08-4144-854e-04dd04eea23a", deviceData);
//        System.out.println(deviceForDB);
    }

    @Test
    public void d() throws Exception {
        //
//        String message = deviceDAO.deleteDevice("DV544da750-cf08-4144-854e-04dd04eea23a");
//        System.out.println(message);
    }

    @Test
    public void rDeviceListByFAD() throws Exception {

        String func = "Switch";
        String asp = "Electronicpower";
        String loc = "http://www.iotoasis.org/ontology/t1eng_605";

        List<DeviceForDB> deviceForDBList = deviceDAO.retrieveDeviceList(func, asp, loc);
        System.out.println(deviceForDBList);

    }
}
