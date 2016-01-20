package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.StudentDetailInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;

import java.util.List;

/**
 * Created by Melvin on 2016. 1. 13..
 * 인물의 상세정보를 생성하는 액션
 */
public class MakeDetailInfoAboutPerson extends DefaultAction {

    DefaultStudent student;

    @Override
    public void execute(IGenericContext context) {

        StudentDetailInfoProvider studentDetailInfoProvider = new StudentDetailInfoProvider();

        student = ((DefaultStudent)context.getValue(IotServiceContext.ACTION_TARGET_STUDENT));

        /**
         * 학생의 URI를 넣은 DefaultSutudent를 이용하여
         * 조회된 studentList(학생의 상세 정보를 포함)를 ACTION_STUDENT_INFO 를 value 값으로 설정
         */

        List<DefaultStudent> studentList = studentDetailInfoProvider.getStudentExtraInfoFromSDA(student);

        context.addValue(IotServiceContext.ACTION_STUDENT_DETAIL, studentList);

    }
}

