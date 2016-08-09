package com.pineone.icbms.so.bizcontext.logic;

import com.pineone.icbms.so.bizcontext.proxy.BizContextProxy;
import com.pineone.icbms.so.bizcontext.proxy.BizContextSDAProxy;

/**
 * Created by melvin on 2016. 8. 3..
 * NOTE: 다음수업에 필요한 마우스의 수와 현재 마우스 수를 조회해서 키보드의 부족한 개수를 도출
 */
public class LackMouseBizLogic extends AbstractBizContextLogic
{

    public static LackMouseBizLogic newLackMouseBizLogic(){
        return new LackMouseBizLogic();
    }
    private BizContextProxy bizContextProxy = BizContextSDAProxy.newBizContextProxy();

    @Override
    public int retrieveNeedQuantity(){
        int currentValue = bizContextProxy.retrieveCurrentClassMouseAmount();
        int nextValue = bizContextProxy.retrieveNextClassMouseAmount();

        return nextValue - currentValue;
    }

}
