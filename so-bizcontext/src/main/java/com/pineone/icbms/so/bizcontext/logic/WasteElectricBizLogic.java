package com.pineone.icbms.so.bizcontext.logic;

import com.pineone.icbms.so.bizcontext.proxy.BizContextProxy;
import com.pineone.icbms.so.bizcontext.proxy.BizContextSDAProxy;
import com.pineone.icbms.so.domain.entity.Domain;

/**
 * Created by melvin on 2016. 8. 3..
 * NOTE: 시나리오상 전력낭비에 대한 데이터 수신
 * NOTE: 디바이스들의 총 예상 전력 소비량 < 실제 모든 디바이스의 현재 전력 소비량
 * */
public class WasteElectricBizLogic extends AbstractBizContextLogic{

    public static WasteElectricBizLogic newWasteElectricBizLogic(){
        return new WasteElectricBizLogic();
    }
    BizContextProxy bizContextProxy = BizContextSDAProxy.newBizContextProxy();

    @Override
    public boolean isHappenBizContext(Domain domain){
        int currentValue = bizContextProxy.retrieveCurrentValue(domain);
        int useAmount = bizContextProxy.retrieveAllDeviceUseAmount(domain);

        if(currentValue > useAmount){
            return true;
        }
        return false;
    }

}
