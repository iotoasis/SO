package com.pineone.icbms.so.interfaces.database.service;

import com.pineone.icbms.so.interfaces.database.dao.AspectDao;
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
public class IAspectMapperTest {

    @Autowired
    AspectDao dao;

    @Test
    public void createTracking() throws Exception {

    }

}