package com.pineone.icbms.so.interfaces.database;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.FunctionalityData;
import com.pineone.icbms.so.interfaces.database.logic.FunctionalityDAO;
import com.pineone.icbms.so.interfaces.database.model.FunctionalityForDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class Functionality_CRUD_Test {
    //
    @Autowired
    FunctionalityDAO functionalityDAO;

    @Test
    public void c() throws Exception {
        //
        FunctionalityData functionalityData = new FunctionalityData("kt func", "www.kt.fun", "functionality");
        FunctionalityForDB functionalityForDB = functionalityDAO.createFunctionality(functionalityData);
        System.out.println(functionalityForDB.toString());
    }

    @Test
    public void r() throws Exception {
        //
//        FunctionalityForDB functionalityForDB = functionalityDAO.retrieveFunctionality("1");
//        System.out.println(functionalityForDB.toString());
    }

    @Test
    public void rList() throws Exception {
        //
        List<FunctionalityForDB> functionalityForDBList = functionalityDAO.retrieveFunctionalityList();
        System.out.println(functionalityForDBList);
    }

    @Test
    public void u() throws Exception {
        //
//        FunctionalityData functionalityData = new FunctionalityData("new Func", "www.new.func", "새로운 Func");
//        FunctionalityForDB functionalityForDB = functionalityDAO.updateFunctionality("FC6954b505-f924-409f-9056-1b83eff8d983", functionalityData);
//        System.out.println(functionalityForDB.toString());
    }

    @Test
    public void d() throws Exception {
        //
//        String resultMessage = functionalityDAO.deleteFunctionality("FC1a271724-754a-46ea-9730-716719052404");
//        System.out.println("**" + resultMessage);
    }
}
