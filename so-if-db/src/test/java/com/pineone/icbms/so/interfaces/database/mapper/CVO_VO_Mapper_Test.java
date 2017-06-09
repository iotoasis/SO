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
@AutoConfigureTestDatabase(replace = NONE) //Jpa embaded DB 로 대체하는것을 금지 , 사용자 설정 디비로 테스트 환경 구성
//@TransactionConfiguration(defaultRollback=false) // Test 환경에서도 DB 에 기록할수 있도록 설정 / 롤백 금지
public class CVO_VO_Mapper_Test {

//    @Autowired
//    CVO_VO_Mapper_DAO cvo_vo_mapper_dao;
//
//    @Test
//    public void c() throws Exception {
//        //
//        String cvo_id = "cvo_kt";
//        String vo_id = "vo_kt";
//
//        CVO_VO_MapperForDB cvo_vo_mapperForDB = cvo_vo_mapper_dao.createCVO_VO_Mapper(cvo_id, vo_id);
//        System.out.println(cvo_vo_mapperForDB);
//    }
//
//    @Test
//    public void r() throws Exception {
//        //
////        CVO_VO_MapperForDB cvo_vo_mapperForDB = cvo_vo_mapper_dao.retrieveCVO_VO_Mapper("1");
////        System.out.println(cvo_vo_mapperForDB);
//    }
//
//    @Test
//    public void rList() throws Exception {
//        //
//        List<CVO_VO_MapperForDB> cvo_vo_mapperForDBList = cvo_vo_mapper_dao.retrieveCVO_VO_Mapper();
//        System.out.println(cvo_vo_mapperForDBList);
//    }
//
//    @Test
//    public void u() throws Exception {
//        //
////        String cvoId = "23123";
////        List<String> voIdList = new ArrayList<>();
////        voIdList.add("231");
////
////        List<CVO_VO_MapperForDB> cvo_vo_mapperForDBList = cvo_vo_mapper_dao.updateCVO_VO_Mapper(cvoId, voIdList);
////        System.out.println(cvo_vo_mapperForDBList);
//    }
//
//    @Test
//    public void d() throws Exception {
//        //
////        String result = cvo_vo_mapper_dao.deleteCVO_VO_Mapper("CVM-af3908b1-6daf-4c22-93de-225f4db465f1");
////        System.out.println(result);
//    }
}
