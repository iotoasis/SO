package com.pineone.icbms.so.bizcontext.logic;

import com.pineone.icbms.so.bizcontext.entity.InefficientElectricBiz;
import com.pineone.icbms.so.bizcontext.proxy.BizContextProxy;
import com.pineone.icbms.so.bizcontext.proxy.BizContextSDAProxy;
import com.pineone.icbms.so.domain.entity.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 8. 3..
 * NOTE: 시나리오상 10분전 전력보다 현재의 전력이 10% 넘게 증가했는지 판단
 * NOTE: 현재 전력 사용량 > (time) 이전 전력 사용량 * 10%
 */

@Service
public class InefficientElectricBizLogic extends AbstractBizContextLogic{

//    public static InefficientElectricBizLogic newInefficientElectricBizLogic(){
//        return new InefficientElectricBizLogic();
//    }

    @Autowired
    BizContextProxy bizContextProxy;

    @Override
    public boolean isHappenBizContext(Domain domain) {

        int currentValue = bizContextProxy.retrieveCurrentValue(domain);
        int pastValue = bizContextProxy.retrievePastValue(domain);

        if(currentValue > pastValue * 1.1){
            return true;
        }
        return false;
    }
}
