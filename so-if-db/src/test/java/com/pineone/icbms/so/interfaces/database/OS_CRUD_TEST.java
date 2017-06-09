package com.pineone.icbms.so.interfaces.database;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.OrchestrationServiceData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IOrchestrationServiceDAO;
import com.pineone.icbms.so.interfaces.database.logic.itf.IVirtualObjectDAO;
import com.pineone.icbms.so.interfaces.database.model.OrchestrationServiceForDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Created by melvin on 2017. 4. 14..
 */

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE) //Jpa embaded DB 로 대체하는것을 금지 , 사용자 설정 디비로 테스트 환경 구성
//@TransactionConfiguration(defaultRollback=false) // Test 환경에서도 DB 에 기록할수 있도록 설정 / 롤백 금지
public class OS_CRUD_TEST {

    @Autowired
    private IOrchestrationServiceDAO orchestrationServiceDAO;

    @Autowired
    private IVirtualObjectDAO virtualObjectDAO;

    @Test
    public void r() {

//      NOTE : 조인결과 조회시 확인 가능
//        OrchestrationServiceForDB orchestrationServiceForDB = orchestrationServiceDAO.retrieveOrchestrationService("1");
//        System.out.println(orchestrationServiceForDB.toString());
    }

    @Test
    public void rList() throws Exception {
        List<OrchestrationServiceForDB> orchestrationServiceForDBList = orchestrationServiceDAO.retrieveOrchestrationServiceList();
        System.out.println(orchestrationServiceForDBList);
    }

    @Test
    public void c() throws Exception {

        OrchestrationServiceData orchestrationServiceData = new OrchestrationServiceData();
        List<String> voIdList = new ArrayList<>();
        voIdList.add("1");
        voIdList.add("2");

        orchestrationServiceData.setName("kt_OS");
        orchestrationServiceData.setDescription("새로운 OS");
        orchestrationServiceData.setParent_id("??");
        orchestrationServiceData.setVirtualObjectIdList(voIdList);

        OrchestrationServiceForDB orchestrationServiceForDB = orchestrationServiceDAO.createOrchestrationService(orchestrationServiceData);
        System.out.println(orchestrationServiceForDB.toString());
    }

    @Test
    public void u() throws Exception {
        //
//        OrchestrationServiceData orchestrationServiceData = new OrchestrationServiceData();
//        List<String> voIdList = new ArrayList<>();
//        voIdList.add("VObfa88eec-ed14-4b7a-a40a-d5753aca518e");
//        voIdList.add("VO5fa177f2-3ccc-490f-ad7f-a8d108043fcf");
//
//        orchestrationServiceData.setName("new OS");
//        orchestrationServiceData.setParent_id("new PID");
//        orchestrationServiceData.setDescription("새로운 OS 업데이트");
//        orchestrationServiceData.setVirtualObjectIdList(voIdList);
//
//        OrchestrationServiceForDB orchestrationServiceForDB = orchestrationServiceDAO.updateOrchestrationService("OS268e44e8-2fdf-4c8e-8995-c61e2159974d", orchestrationServiceData);
//        System.out.println(orchestrationServiceForDB.toString());
    }

    @Test
    public void d() throws Exception {
//        String deleteMessage = orchestrationServiceDAO.deleteOrchestrationService("OS6fb60778-7415-4d22-91ae-6e16e80ffc10");
//        System.out.println(deleteMessage.toString());
    }
}
