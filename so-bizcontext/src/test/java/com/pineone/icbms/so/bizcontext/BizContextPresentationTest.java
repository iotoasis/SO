package com.pineone.icbms.so.bizcontext;

import com.pineone.icbms.so.bizcontext.pr.BizContextPresentation;
import com.pineone.icbms.so.bizcontext.ref.ResponseMessage;
import org.junit.Test;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 3..
 */
public class BizContextPresentationTest {

    BizContextPresentation bizContextPresentation = new BizContextPresentation();

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
        ResponseMessage responseMessage = bizContextPresentation.registerBizContext("LACK_PC");
        System.out.println(responseMessage.getMessage());
    }

//    @Test
//    public void 비효율상황발생여부체크() throws Exception {
//        boolean result = bizContextPresentation.isBizContextHappen(Biz_Note.INEFFICIENT_ELECTRIC.toString());
//        System.out.println(result);
//    }
}
