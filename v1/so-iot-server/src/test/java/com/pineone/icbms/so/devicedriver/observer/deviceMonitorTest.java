package com.pineone.icbms.so.devicedriver.observer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pineone.icbms.so.TestUtil;
import com.pineone.icbms.so.iot.resources.message.ResultMessage;
import com.pineone.icbms.so.server.config.WebAppContext;

/**
 * Created by use on 2015-10-28.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppContext.class)
@WebAppConfiguration
public class deviceMonitorTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mock;

    private ResultMessage test;

    @Before
    public void setUp() throws Exception
    {
        this.mock = MockMvcBuilders.webAppContextSetup(wac).build();
        test = new ResultMessage();
        test.set_result("success");
        test.set_resultCode("200");
    }

    public ResultActions getResultActions(Object object) throws Exception
    {
        return mock.perform(MockMvcRequestBuilders.post("/resources/vdcm/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(object)));
    }

    @Test
    public void testMock() throws Exception
    {
        ResultActions resultActions = getResultActions(test);
        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}
