package com.pineone.icbms.so.interfaces.database;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.AspectData;
import com.pineone.icbms.so.interfaces.database.logic.AspectDAO;
import com.pineone.icbms.so.interfaces.database.model.AspectForDB;
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
 * Created by melvin on 2017. 4. 17..
 */


@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE) //Jpa embaded DB 로 대체하는것을 금지 , 사용자 설정 디비로 테스트 환경 구성
//@TransactionConfiguration(defaultRollback=false) // Test 환경에서도 DB 에 기록할수 있도록 설정 / 롤백 금지
public class Aspect_CRUD_Test {

    @Autowired
    private AspectDAO aspectDAO;

    @Test
    public void c() throws Exception {
        //
        AspectData aspectData = new AspectData("kt as", "www.kt.as", "aspect");
        AspectForDB aspectForDB = aspectDAO.createAspect(aspectData);
        System.out.println(aspectForDB.toString());
    }

    @Test
    public void r() throws Exception {
//        AspectForDB aspectForDB = aspectDAO.retrieveAspect("Electronicpower");
//        System.out.println(aspectForDB.toString());
    }

    @Test
    public void rList() throws Exception {
        List<AspectForDB> aspectForDBList = aspectDAO.retrieveAspectList();
        System.out.println(aspectForDBList);
    }

    @Test
    public void u() throws Exception {

//        AspectData aspectData = new AspectData("new As", "www.new.as", "새로운 Aspect");
//        AspectForDB aspectForDB = aspectDAO.updateAspect("AP-0c72e8a0-3758-49b4-bcd6-b822ce2cc888", aspectData);
//        System.out.println(aspectForDB.toString());
    }

    @Test
    public void d() throws Exception {

//        String resultMessage = aspectDAO.deleteAspect("AP-0c72e8a0-3758-49b4-bcd6-b822ce2cc888");
//        System.out.println("**" + resultMessage);
    }
}
