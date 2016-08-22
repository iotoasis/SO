package com.pineone.icbms.so.bizcontext.logic;

import com.pineone.icbms.so.bizcontext.proxy.BizContextProxy;
import com.pineone.icbms.so.bizcontext.proxy.BizContextSDAProxy;
import com.pineone.icbms.so.domain.entity.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 8. 3..
 * NOTE: 다음수업에 필요한 키보드의 수와 현재 키보드 수를 조회해서 키보드의 부족한 개수를 도출
 */

@Service
public class LackKeyboardBizLogic extends AbstractBizContextLogic{

//    public static LackKeyboardBizLogic newLackKeyboardBizLogic(){
//        return new LackKeyboardBizLogic();
//    }

    @Autowired
    private BizContextProxy bizContextProxy;

    @Override
    public int retrieveNeedQuantity(Domain domain){
        int currentValue = bizContextProxy.retrieveCurrentClassKeyBoardAmount(domain);
        int nextValue = bizContextProxy.retrieveNextClassKeyBoardAmount(domain);

        return nextValue - currentValue;
    }
}
