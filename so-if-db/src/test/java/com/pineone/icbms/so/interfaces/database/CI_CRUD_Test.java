package com.pineone.icbms.so.interfaces.database;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.ContextInformationData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IContextInformationDAO;
import com.pineone.icbms.so.interfaces.database.model.ContextInformationForDB;
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
 * Created by melvin on 2017. 4. 11..
 */

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE) //Jpa embaded DB 로 대체하는것을 금지 , 사용자 설정 디비로 테스트 환경 구성
//@TransactionConfiguration(defaultRollback=false) // Test 환경에서도 DB 에 기록할수 있도록 설정 / 롤백 금지
public class CI_CRUD_Test {

    @Autowired
    private IContextInformationDAO contextInformationDAO;

    @Test
    public void c() throws Exception {
        ContextInformationData contextInformationData = new ContextInformationData("icbms-ci-test", "www.iotoasis.test", "테스트CI");
        contextInformationDAO.createContextInformation(contextInformationData);
    }

    @Test
    public void r() throws Exception {
//        ContextInformationForDB contextInformationForDB = contextInformationDAO.retrieveContextInformation("CI78d35d5c-17d8-43a9-894b-720331781ae1");
//        System.out.println(contextInformationForDB);
    }

    @Test
    public void rList() {
        List<ContextInformationForDB> contextInformationForDBList = contextInformationDAO.retrieveContextInformationList();
        System.out.println(contextInformationForDBList);
    }

    @Test
    public void update() throws Exception {

//        ContextInformationData contextInformationData = new ContextInformationData("icbms-ci-test-변경", "www.iotoasis.test.change", "테스트CI_변경");
//        ContextInformationForDB contextInformationForDB =
//                contextInformationDAO.updateContextInformation("CI78d35d5c-17d8-43a9-894b-720331781ae1", contextInformationData);
//
//        System.out.println(contextInformationForDB);
    }

    @Test
    public void delete() throws Exception {
//        String deleteMessage = contextInformationDAO.deleteContextInformation("CI78d35d5c-17d8-43a9-894b-720331781ae1");
//        System.out.println(deleteMessage.toString());
    }
}
