package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.LocationInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;

import java.util.List;

/**
 * 인물의 현재 위치 검색
 * Created by Melvin on 2016. 1. 13..
 */
public class MakeCurrentLocationInfoAction extends DefaultAction {

    DefaultStudent student;


    @Override
    public void execute(IGenericContext context) {

        LocationInfoProvider locationInfoProvider = new LocationInfoProvider();

        student = ((DefaultStudent)context.getValue(IotServiceContext.ACTION_TARGET_STUDENT));

        /**
         * String형 time으로 이벤트(알람)을 발생시킬 학생목록 리스트 조회
         * 조회된 studentList를 ACTION_TARGET_STUDENT value 값으로 설정
         */

        List<DefaultLocation> locationList = locationInfoProvider.getCurrentLocationFromSDA(student);

        context.addValue(IotServiceContext.ACTION_TARGET_LOCATION, locationList);

    }
}
