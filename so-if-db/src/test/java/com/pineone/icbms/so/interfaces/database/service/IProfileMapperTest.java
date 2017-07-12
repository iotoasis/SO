package com.pineone.icbms.so.interfaces.database.service;

import com.pineone.icbms.so.interfaces.database.dao.ProfileDao;
import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jonghee on 2017-06-15.
 * test/resource
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(value = {"com.pineone.icbms.so.interfaces.database.service"})
public class IProfileMapperTest {

    @Autowired
    ProfileDao dao;

    @Test
    public void createTracking() throws Exception {
        ProfileForDB profile = dao.retrieveProfile("PR-810fc6d0-1e08-4875-adc5-5c4adce00e33");
        System.out.println("조회결과 : " + profile.getId());
    }

}