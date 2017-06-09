package com.pineone.icbms.so.interfaces.database;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.CompositeVirtualObjectData;
import com.pineone.icbms.so.interfaces.database.logic.itf.ICompositeVirtualObjectDAO;
import com.pineone.icbms.so.interfaces.database.logic.itf.IVirtualObjectDAO;
import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Created by melvin on 2017. 3. 30..
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE) //Jpa embaded DB 로 대체하는것을 금지 , 사용자 설정 디비로 테스트 환경 구성
//@TransactionConfiguration(defaultRollback=false) // Test 환경에서도 DB 에 기록할수 있도록 설정 / 롤백 금지
public class CVO_Crud_Test {

    @Autowired
    private ICompositeVirtualObjectDAO compositeVirtualObjectDAO;

    @Test
    public void r() {
    //조인결과 조회시 확인 가능
        CompositeVirtualObjectForDB compositeVirtualObjectForDB = compositeVirtualObjectDAO.retrieveCompositeVirtualObject("cvo-fire-env");
        System.out.println(compositeVirtualObjectForDB);
    }

    @Test
    public void rList() throws Exception {
        List<CompositeVirtualObjectForDB> compositeVirtualObjectList = compositeVirtualObjectDAO.retrieveCompositeVirtualObjectList();
        System.out.println(compositeVirtualObjectList);
    }

    @Test
    public void c() throws Exception {

        CompositeVirtualObjectData compositeVirtualObjectData = new CompositeVirtualObjectData();
        List<String> voIdList = new ArrayList<>();
        voIdList.add("vo-powerswitch");
        voIdList.add("vo-powerswitch1");

        compositeVirtualObjectData.setAspect_id("1");
        compositeVirtualObjectData.setDescription("새로운 CVO");
        compositeVirtualObjectData.setFunctionality_id("1");
        compositeVirtualObjectData.setName("ktCVO");
        compositeVirtualObjectData.setType("TYPE!!!!!?");
        compositeVirtualObjectData.setVirtualObjectIdList(voIdList);

        CompositeVirtualObjectForDB compositeVirtualObjectForDB = compositeVirtualObjectDAO.createCompositeVirtualObject(compositeVirtualObjectData);
        System.out.println(compositeVirtualObjectForDB);
    }

    @Test
    public void u() throws Exception {
        //
//        CompositeVirtualObjectData compositeVirtualObjectData = new CompositeVirtualObjectData();
//        List<String> voIdList = new ArrayList<>();
//        voIdList.add("2");
//        voIdList.add("1");
//
//        compositeVirtualObjectData.setAspect_id("1");
//        compositeVirtualObjectData.setDescription("새로운 CVO 바꿔치기");
//        compositeVirtualObjectData.setFunctionality_id("1");
//        compositeVirtualObjectData.setName("ktCVO Update");
//        compositeVirtualObjectData.setType("TYPE!!!!!?");
//        compositeVirtualObjectData.setVirtualObjectIdList(voIdList);
//
//        CompositeVirtualObjectForDB compositeVirtualObjectForDB = compositeVirtualObjectDAO.updateCompositeVirtualObject("CVO6e9d81a7-9427-4842-b332-721be598594e", compositeVirtualObjectData);
//        System.out.println(compositeVirtualObjectForDB);
    }

    @Test
    public void d() throws Exception {
//        String result = compositeVirtualObjectDAO.deleteCompositeVirtualObject("CVO6e9d81a7-9427-4842-b332-721be598594e");
//        System.out.println(result);
    }
}
