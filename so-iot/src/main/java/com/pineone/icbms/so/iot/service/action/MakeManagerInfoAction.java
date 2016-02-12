package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.PersonInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;

import java.util.List;

/**
 * 연구실의 관리인 정보 검색하는 액션
 * Created by Melvin on 2016. 1. 12..
 */
public class MakeManagerInfoAction extends DefaultAction {

    DefaultLocation location ;

    @Override
    public void execute(IGenericContext context) {

        PersonInfoProvider personInfoProvider = new PersonInfoProvider();

        /**
         * Location정보를 DefaultLocation객체에 넣어서 SDA인터페이스 사용(DeviceInfoProvider)
         * 해당 장소의 관리인 리스트(Studentlist)를 Action_STYDENT_MANAGER 의 value 값으로 설정
         */

        location = (DefaultLocation) context.getValue(IotServiceContext.ACTION_TARGET_LOCATION);

        List<DefaultStudent> studentList= personInfoProvider.getManagerInfoFromSDA(location);

        context.addValue(IotServiceContext.ACTION_STUDENT_MANAGER, studentList);
    }
}
