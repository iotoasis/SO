package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.StudentDetailInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Melvin on 2016. 1. 13..
 * get Detail Information About Person<BR/>
 */
public class MakeDetailInfoAboutPerson extends DefaultAction {

    private final Logger log = LoggerFactory.getLogger(MakeDetailInfoAboutPerson.class);

    DefaultStudent student;

    @Override
    public void execute(IGenericContext context) {

        StudentDetailInfoProvider studentDetailInfoProvider = new StudentDetailInfoProvider();

        student = ((DefaultStudent)context.getValue(IotServiceContext.ACTION_TARGET_STUDENT));

        /**
         * Use studentDetailInfoProvider that has SDA Interface(get Student Extra Info) <BR/>
         * add Detail Information of Student to ACTION_STUDENT_DETAIL<BR/>
         */

        List<DefaultStudent> studentList = studentDetailInfoProvider.getStudentExtraInfoFromSDA(student);

        for(DefaultStudent student : studentList ){
            log.info(" >> StudentId : " + student.getId());
            log.info(" >> StudentName : " + student.getName());
            log.info(" >> Student's Phone Number : " + student.getPhoneNum());
        }

        context.addValue(IotServiceContext.ACTION_STUDENT_DETAIL, studentList);

    }
}

