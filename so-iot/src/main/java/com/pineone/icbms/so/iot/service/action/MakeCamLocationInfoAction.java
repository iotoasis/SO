package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.DeviceInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;

import java.util.List;

/**
 * Created by Melvin on 2016. 1. 13..
 * 지정된 장소의 캠 정보(uri) 를 가져오는 액션
 */
public class MakeCamLocationInfoAction extends DefaultAction{

    DefaultLocation location ;

    @Override
    public void execute(IGenericContext context) {

        DeviceInfoProvider deviceInfoProvider = new DeviceInfoProvider();

        /**
         * Location정보를 DefaultLocation객체에 넣어서 SDA인터페이스 사용(DeviceInfoProvider)
         * DeviceList를 Action_Device_URI 의 value 값으로 설정
         */

        location = (DefaultLocation) context.getValue(IotServiceContext.ACTION_TARGET_LOCATION);

        List<DefaultPhysicalDevice> deviceList = deviceInfoProvider.getCamInfoFromSDA(location);

        context.addValue(IotServiceContext.ACTION_DEVICE_URI, deviceList);
    }
}
