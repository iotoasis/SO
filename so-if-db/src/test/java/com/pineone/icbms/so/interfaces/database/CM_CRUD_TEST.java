package com.pineone.icbms.so.interfaces.database;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.CompositeVirtualObjectData;
import com.pineone.icbms.so.interfaces.database.controller.inputdata.ContextModelData;
import com.pineone.icbms.so.interfaces.database.logic.ContextModelDAO;
import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.ContextModelForDB;
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
 * Created by melvin on 2017. 4. 17..
 */

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE) //Jpa embaded DB 로 대체하는것을 금지 , 사용자 설정 디비로 테스트 환경 구성
//@TransactionConfiguration(defaultRollback=false) // Test 환경에서도 DB 에 기록할수 있도록 설정 / 롤백 금지
public class CM_CRUD_TEST {

    @Autowired
    ContextModelDAO contextModelDAO;

    @Test
    public void r() throws Exception {
        ContextModelForDB contextModelForDB = contextModelDAO.retrieveContextModel("cm-notideal-light-lecture");
        System.out.println(contextModelForDB.toString());
    }

    @Test
    public void rList() throws Exception {
        List<ContextModelForDB> contextModelForDBList = contextModelDAO.retrieveContextModelList();
        System.out.println(contextModelForDBList.toString());
    }

    @Test
    public void c() throws Exception {
        //
        ContextModelData contextModelData = new ContextModelData();

        List<String> ciIdList = new ArrayList<>();
        ciIdList.add("ci-test1");
        ciIdList.add("ci-test2");

        contextModelData.setName("ktCM");
        contextModelData.setDescription("새로운 CVO");
        contextModelData.setContextInformationIdList(ciIdList);

        ContextModelForDB contextModelForDB = contextModelDAO.createContextModel(contextModelData);
        System.out.println(contextModelForDB);
    }

    @Test
    public void u() throws Exception {
        //
//        ContextModelData contextModelData = new ContextModelData();
//        List<String> ciIdList = new ArrayList<>();
//        ciIdList.add("1");
//        ciIdList.add("234");
//        ciIdList.add("453");
//
//        contextModelData.setName("new CM");
//        contextModelData.setDescription("new DES");
//        contextModelData.setContextInformationIdList(ciIdList);
//        ContextModelForDB contextModelForDB = contextModelDAO.updateContextModel("CM-6fb46444-492f-4487-91f3-c6bcc9cd7c90", contextModelData);
//        System.out.println(contextModelForDB.toString());
    }

    @Test
    public void d() throws Exception {
        //
//        String deleteMessage = contextModelDAO.deleteContextModel("CM-b9847b05-8379-4b00-a650-51a5b7ca2bae");
//        System.out.println(deleteMessage.toString());
    }
}
