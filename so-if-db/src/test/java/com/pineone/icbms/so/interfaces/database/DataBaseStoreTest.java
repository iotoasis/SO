package com.pineone.icbms.so.interfaces.database;

import com.pineone.icbms.so.interfaces.database.logic.ProfileDAO;
import com.pineone.icbms.so.interfaces.database.logic.DataBaseStoreDAO;
import com.pineone.icbms.so.interfaces.database.model.ContextInformationForDB;
import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Created by melvin on 2017. 5. 10..
 */

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE) //Jpa embaded DB 로 대체하는것을 금지 , 사용자 설정 디비로 테스트 환경 구성//Jpa embaded DB 로 대체하는것을 금지 , 사용자 설정 디비로 테스트 환경 구성
//@TransactionConfiguration(defaultRollback=false) // Test 환경에서도 DB 에 기록할수 있도록 설정 / 롤백 금지
public class DataBaseStoreTest {

    @Autowired
    ProfileDAO profileDAO;

    @Autowired
    DataBaseStoreDAO dataBaseStoreDAO;

    @Test
    public void name() throws Exception {
        //
        // 응급상황 발생에 따른 ProfileList 검색
        List<ProfileForDB> profileForDBList = profileDAO.
                getProfileByContextModelAndLocation("cm-notideal-light-lecture", "http://www.iotoasis.org/ontology/t1eng_605");
        for (ProfileForDB profileForDB : profileForDBList) {
            System.out.println("***************" + profileForDB.toString());

            System.out.println("########### ProfileId = " + profileForDB.getId());
            System.out.println("########### OrchestrationId = " + profileForDB.getOrchestrationServiceId());

            List<VirtualObjectForDB> voList = dataBaseStoreDAO.getVirtualObjectListByOrchestrationId(profileForDB.getOrchestrationServiceId());

            for (VirtualObjectForDB virtualObjectForDB : voList) {
                System.out.println("########### voID = " + virtualObjectForDB);
            }
        }
    }

    @Test
    public void retrieveVirtualObjectIdListByCVOID() throws Exception {
        //
//        List<VirtualObjectForDB> virtualObjectForDBList = dataBaseStoreDAO.getVirtualObjectListByCompositeVirtualObjectId("CVO1290f843-3baf-456e-b000-b582951b2139");
//        System.out.println(virtualObjectForDBList);
    }

    @Test
    public void retrieveContextInformationListByCMId() throws Exception {
        //
        List<ContextInformationForDB> contextInformationForDBList = dataBaseStoreDAO.getContextInformationListByContextModelId("cm-notideal-light-lecture");
        System.out.println(contextInformationForDBList);
    }


}
