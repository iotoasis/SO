package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.OptimalValueInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.value.DefaultValue;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;

/**
 * 입력받은 장소의 적정 온도를 검색하는 액션
 * Created by Melvin on 2016. 1. 12..
 */
public class MakeValueInfoAction extends DefaultAction{

    DefaultLocation location ;

    @Override
    public void execute(IGenericContext context) {

        OptimalValueInfoProvider valueInfoProvider = new OptimalValueInfoProvider();

        location = (DefaultLocation) context.getValue(IotServiceContext.ACTION_TARGET_LOCATION);


        /**
         * Location정보를 DefaultLocation객체에 넣어서 SDA인터페이스 사용(valueInfoProvider)
         * 최소, 최대값이 저장되어 있는 value값을 ACTION_OPTIMAL_TEMP 의 value 값으로 설정
         */

        DefaultValue value = valueInfoProvider.getOptimalValueFromSDA(location);

        context.addValue(IotServiceContext.ACTION_OPTIMAL_TEMP, value);

    }
}
