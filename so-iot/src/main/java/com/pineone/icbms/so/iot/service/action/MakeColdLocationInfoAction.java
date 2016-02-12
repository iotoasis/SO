package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.LocationInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;

import java.util.List;

/**
 *  * 적정 온도보다 낮은 장소 조회 <BR/>
 * Created by Melvin on 2016. 1. 20..
 */
public class MakeColdLocationInfoAction extends DefaultAction {

    DefaultLocation location;


    @Override
    public void execute(IGenericContext context) {

        LocationInfoProvider locationInfoProvider = new LocationInfoProvider();

        location = ((DefaultLocation) context.getValue(IotServiceContext.ACTION_TARGET_LOCATION));

        /**
         * 수신받은 Locaiton 정보를 이용하여 그 Location이 현재 적정 온도보다 낮은지 조회.<BR/>
         * 온도보다 낮다면 Location을 정보를 반환 <BR/>
         */

        List<DefaultLocation> locationList = locationInfoProvider.getColdLocationInfo(location);

        context.addValue(IotServiceContext.ACTION_TARGET_LOCATION, locationList);

    }
}
