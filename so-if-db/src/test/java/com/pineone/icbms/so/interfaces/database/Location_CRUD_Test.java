package com.pineone.icbms.so.interfaces.database;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.LocationData;
import com.pineone.icbms.so.interfaces.database.logic.LocationDAO;
import com.pineone.icbms.so.interfaces.database.model.LocationForDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Created by melvin on 2017. 4. 18..
 */

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE) //Jpa embaded DB 로 대체하는것을 금지 , 사용자 설정 디비로 테스트 환경 구성
//@TransactionConfiguration(defaultRollback=false) // Test 환경에서도 DB 에 기록할수 있도록 설정 / 롤백 금지
public class Location_CRUD_Test {
    //
    @Autowired
    LocationDAO locationDAO;

    @Test
    public void c() throws Exception {
        LocationData locationData = new LocationData("kt Loc", "www.kt.loc", "location");
        LocationForDB locationForDB = locationDAO.createLocation(locationData);
        System.out.println(locationForDB.toString());
    }

    @Test
    public void r() throws Exception {
//        LocationForDB locationForDB = locationDAO.retrieveLocation("http://www.iotoasis.org/ontology/t1eng_605");
//        System.out.println(locationForDB.toString());
    }

    @Test
    public void rList() throws Exception {
        List<LocationForDB> locationForDBList = locationDAO.retrieveLocationList();
        System.out.println(locationForDBList);
    }

    @Test
    public void u() throws Exception {
//        LocationData locationData = new LocationData("new Loc", "www.new.loc", "new location");
//        LocationForDB locationForDB = locationDAO.updateLocation("LC17bbc392-a306-4878-aff2-d7c31c546c00", locationData);
//        System.out.println(locationForDB.toString());
    }

    @Test
    public void d() throws Exception {
//        String resultMessage = locationDAO.deleteLocation("LC73179e19-90af-4259-b169-d0230e909c6a");
//        System.out.println(resultMessage);
    }
}
