package com.pineone.icbms.so.bizcontext.logic;

import com.pineone.icbms.so.bizcontext.proxy.BizContextProxy;
import com.pineone.icbms.so.bizcontext.proxy.BizContextSDAProxy;
import com.pineone.icbms.so.domain.entity.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 8. 3..
 * NOTE: 시나리오상 목표전력 사용량 시나리오 반영
 * NOTE: 목표전력사용량 ( 날씨에 따라 사용할 전력량 예측 ) < 현재 전력량
 */

@Service
public class CurrentElectricBizLogic extends AbstractBizContextLogic{

//    public static CurrentElectricBizLogic newCurrentElectricBizLogic(){
//        return new CurrentElectricBizLogic();
//    }

    @Autowired
    BizContextProxy bizContextProxy;

    @Override
    public boolean isHappenBizContext(Domain domain){

        int currentValue = bizContextProxy.retrieveCurrentValue(domain);
        int objectValue = bizContextProxy.retrieveObjectValue(domain);

        if(currentValue > objectValue){
            return true;
        }
        return false;
    }
}
