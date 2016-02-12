package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.LocationInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;

import java.util.List;

/**
 * 입력하는 스케쥴에 이벤트가 있는 장소 정보를 획득하는 액션
 * Created by Melvin on 2016. 1. 12..
 */
public class MakeScheduledLocationAction extends DefaultAction{

    @Override
    public void execute(IGenericContext context) {

        LocationInfoProvider locationInfoProvider = new LocationInfoProvider();

        String date = "monday";

        String time= "0850";

        /**
         * String형 date와 time으로 이벤트가 발생하는 장소 정보 획득
         * locationList를 Action_TARGET_LOCATION 의 value 값으로 설정
         */


        List<DefaultLocation> locationList =
                locationInfoProvider.getLocationInfoFromSDA(date, time);

        context.addValue(IotServiceContext.ACTION_TARGET_LOCATION, locationList );

    }
}
