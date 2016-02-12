package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.PersonInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;

import java.util.List;

/**
 * 알람 대상자를 검색하기 위한 액션
 * Created by Melvin on 2016. 1. 12..
 */
public class MakePersonListforAlarmAction extends DefaultAction {

    @Override
    public void execute(IGenericContext context) {

        PersonInfoProvider personInfoProvider = new PersonInfoProvider();

        String time = "08:00";

        /**
         * String형 time으로 이벤트(알람)을 발생시킬 학생목록 리스트 조회
         * 조회된 studentList를 ACTION_TARGET_STUDENT value 값으로 설정
         */


        List<DefaultStudent> studentList =
                personInfoProvider.getPersonInfoAboutAlarm(time);

        context.addValue(IotServiceContext.ACTION_TARGET_STUDENT, studentList);

    }
}
