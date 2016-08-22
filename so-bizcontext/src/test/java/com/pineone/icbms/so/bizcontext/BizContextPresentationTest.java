package com.pineone.icbms.so.bizcontext;

import com.pineone.icbms.so.bizcontext.pr.BizContextPresentation;
import com.pineone.icbms.so.bizcontext.ref.ResponseMessage;
import com.pineone.icbms.so.domain.DomainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 3..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BizContextApplication.class)
public class BizContextPresentationTest {

    @Autowired
    BizContextPresentation bizContextPresentation;

    @Test
    public void biz컨텍스트리스트조회() throws Exception {
        //
        List<String> bizContextList = bizContextPresentation.retrieveBizContextList();
        for(String bizcontext : bizContextList){
            System.out.println(bizcontext);
        }
    }

    @Test
    public void biz컨텍스트선택해서등록() throws Exception {
        //
        ResponseMessage responseMessage = bizContextPresentation.registerBizContext("LACK_PC");
        System.out.println(responseMessage.getMessage());
    }

//    @Test
//    public void 비효율상황발생여부체크() throws Exception {
//        boolean result = bizContextPresentation.isBizContextHappen(Biz_Note.INEFFICIENT_ELECTRIC.toString());
//        System.out.println(result);
//    }
}
