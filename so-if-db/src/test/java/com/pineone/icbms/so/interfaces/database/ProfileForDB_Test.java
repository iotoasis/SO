package com.pineone.icbms.so.interfaces.database;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.ProfileData;
import com.pineone.icbms.so.interfaces.database.logic.ProfileDAO;
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
 * Created by melvin on 2017. 4. 3..
 */

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE) //Jpa embaded DB 로 대체하는것을 금지 , 사용자 설정 디비로 테스트 환경 구성
//@TransactionConfiguration(defaultRollback=false) // Test 환경에서도 DB 에 기록할수 있도록 설정 / 롤백 금지
public class ProfileForDB_Test {

    @Autowired
    ProfileDAO profileDAO;

    @Test
    public void rByCMAndLOC() throws Exception {
        //
        List<ProfileForDB> profileForDBList = profileDAO.
                getProfileByContextModelAndLocation("cm-notideal-light-lecture", "http://www.iotoasis.org/ontology/t1eng_605");
        for (ProfileForDB profileForDB : profileForDBList) {
            System.out.println("***************" + profileForDB.toString());
        }
    }

    @Test
    public void r() throws Exception {
//        long startTime = System.nanoTime();
//        ProfileForDB profileForDB = profileDAO.retrieveProfile("pr-fresh-lecture");
//        System.out.println(profileForDB.toString());
//        long endTime = System.nanoTime();
//        System.out.println("TIME : " + (endTime - startTime)/1000000.0 + "(ms)");
    }

    @Test
    public void rList() throws Exception {
        List<ProfileForDB> profileForDBList = profileDAO.retrieveProfileList();
        System.out.println(profileForDBList);
    }

    @Test
    public void c() throws Exception {
        //
        ProfileData profileData = new ProfileData("testPR", "cm-electric-inefficiency", "1", "http://www.iotoasis.org/ontology/t1eng_605",
                true, "Profile", 10);

        ProfileForDB profileForDB = profileDAO.createProfileByExceptID(profileData);
        System.out.println(profileForDB.toString());
    }

    @Test
    public void u() throws Exception {
        //
//        ProfileData profileData = new ProfileData("new testPR", "1", "1", "LC17bbc392-a306-4878-aff2-d7c31c546c00",
//                true, "newProfile", 10);
//
//        ProfileForDB profileForDB = profileDAO.updateProfile("PRe96c2a0f-1ec4-44c1-95d8-94cc4fe30c72", profileData);
//        System.out.println(profileForDB);
    }

    @Test
    public void d() throws Exception {
//        String resultMessage = profileDAO.deleteProfile("3");
//        System.out.println(resultMessage);
    }

    @Test
    public void retrieveTest() throws Exception {
        List<ProfileForDB> profileforDBList = profileDAO.retrieveProfileListByEnable(true);
        System.out.println("###################" + profileforDBList);
    }

    @Test
    public void profilePeriodUpdate() throws Exception {
        //
//        ProfileForDB profileForDB = profileDAO.retrieveProfile("PR00103ed5-807a-4fd2-890e-7445507b3e3f");
//        profileForDB = profileDAO.updateProfilePeriod(profileForDB.getId(), 500);
//        System.out.println(profileForDB.toString());
    }

    @Test
    public void profileEnabledUpdate() throws Exception {
        //
//        ProfileForDB profileForDB = profileDAO.retrieveProfile("PR00103ed5-807a-4fd2-890e-7445507b3e3f");
//        profileForDB = profileDAO.updateProfileEnabled(profileForDB.getId(), false);
//        System.out.println(profileForDB.toString());
    }
}
