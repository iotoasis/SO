package com.pineone.icbms.so.bizcontext.logic;

import com.pineone.icbms.so.bizcontext.proxy.BizContextProxy;
import com.pineone.icbms.so.bizcontext.proxy.BizContextSDAProxy;

/**
 * Created by melvin on 2016. 8. 3..
 * NOTE: 다음수업에 필요한 PC의 수와 현재 PC 수를 조회해서 키보드의 부족한 개수를 도출
 */
public class LackPCBizLogic extends AbstractBizContextLogic{

    public static LackPCBizLogic newLackPCBizLogic(){
        return new LackPCBizLogic();
    }
    private BizContextProxy bizContextProxy = BizContextSDAProxy.newBizContextProxy();

    @Override
    public int retrieveNeedQuantity(){
        //
        int currentValue = bizContextProxy.retrieveCurrentClassPCAmount();
        int nextValue = bizContextProxy.retrieveNextClassPCAmount();

        return nextValue - currentValue;
    }

}
