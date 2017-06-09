package com.pineone.icbms.so.interfaces.database.mapper;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Created by melvin on 2017. 5. 11..
 */

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE) //Jpa embadded DB 로 대체하는것을 금지 , 사용자 설정 디비로 테스트 환경 구성
//@TransactionConfiguration(defaultRollback=false) // Test 환경에서도 DB 에 기록할수 있도록 설정 / 롤백 금지
public class CM_CI_Mapper_Test {

//    @Autowired
//    CM_CI_Mapper_DAO cm_ci_mapper_dao;
//
//    @Test
//    public void c() throws Exception {
//        //
//        String cm_id = "cm_kt";
//        String ci_id = "ci_kt";
//
//        CM_CI_MapperForDB cm_ci_mapperForDB = cm_ci_mapper_dao.createCM_CI_Mapper(cm_id,ci_id);
//        System.out.println(cm_ci_mapperForDB);
//    }
//
//    @Test
//    public void rByMapperId() throws Exception {
//        //
////        CM_CI_MapperForDB cm_ci_mapperForDB = cm_ci_mapper_dao.retrieveCM_CI_MapperByMapperId("CCM-d48a4290-1b50-42c9-a42a-e51f51e963ff");
////        System.out.println(cm_ci_mapperForDB);
//    }
//
//    @Test
//    public void rList() throws Exception {
//        //
//        List<CM_CI_MapperForDB> cm_ci_mapperForDBList = cm_ci_mapper_dao.retrieveCM_CI_Mapper();
//        System.out.println(cm_ci_mapperForDBList);
//    }
//
//    @Test
//    public void rListByCMId() throws Exception {
//        //
////        List<CM_CI_MapperForDB> cm_ci_mapperForDBList = cm_ci_mapper_dao.retrieveCM_CI_MapperListBYCMId("cm-notideal-light-lecture");
////        System.out.println(cm_ci_mapperForDBList);
//    }
//
//    @Test
//    public void u() throws Exception {
//        //
////        String cm_Id = "cm_kt";
////        List<String> ciIdList = new ArrayList<>();
////        ciIdList.add("ci001");
////        ciIdList.add("ci002");
////        ciIdList.add("ci003");
////
////        List<CM_CI_MapperForDB> cm_ci_mapperForDBList = cm_ci_mapper_dao.updateCM_CI_Mapper(cm_Id,ciIdList);
////        System.out.println(cm_ci_mapperForDBList);
//    }
//
//    @Test
//    public void d() throws Exception {
//        //
////        String result = cm_ci_mapper_dao.deleteCM_CI_Mapper("CCM-c8d580a8-2209-4a30-9cc1-c3526d7617cb");
////        System.out.println(result);
//    }
}
