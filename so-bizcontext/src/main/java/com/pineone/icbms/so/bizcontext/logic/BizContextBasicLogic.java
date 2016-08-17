package com.pineone.icbms.so.bizcontext.logic;

import com.pineone.icbms.so.bizcontext.ref.Biz_Note;
import com.pineone.icbms.so.bizcontext.ref.ResponseMessage;
import com.pineone.icbms.so.domain.entity.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 3..
 * NOTE: 특정 Biz가 아닌 BizContext에 관한 전체적인 로직 포함
 */
public class BizContextBasicLogic {

    public static BizContextBasicLogic newBizContextBasicLogic(){
        return new BizContextBasicLogic();
    }

    //NOTE : BizContext 종류를 조회
    public List<String> retrieveBizContextList() {
        List<String> bizContextList = new ArrayList<>();
        for(Biz_Note biz_note : Biz_Note.values()){
            bizContextList.add(biz_note.toString());
        }
        return bizContextList;
    }

    //NOTE: 등록 요청한 Biz 컨텍스트를 사용자에게 알려주기 위한 메세지 생성
    public String registerBizContext(String bizContextName) {
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        String resultMessage = responseMessage.bizContextResultMessage(bizContextName);
        return resultMessage;
    }
}
