package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.PersonInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * get Person Information that need to Ring Alarm<BR/>
 * Created by Melvin on 2016. 1. 12..
 */
public class MakePersonListforAlarmAction extends DefaultAction {

    private final Logger log = LoggerFactory.getLogger(MakePersonListforAlarmAction.class);

    @Override
    public void execute(IGenericContext context) {

        PersonInfoProvider personInfoProvider = new PersonInfoProvider();

        String time = "08:00";

        /**
         * Use PersonInfoProvider that has SDA Interface(get Person Info About Alarm) <BR/>
         * add Information of STUDENT to ACTION_TARGET_STUDENT  <BR/>
         */

        List<DefaultStudent> studentList =
                personInfoProvider.getPersonInfoAboutAlarm(time);

        for(DefaultStudent student : studentList ){
            log.info(" >> StudentList : " + student.getUri());
        }

        context.addValue(IotServiceContext.ACTION_TARGET_STUDENT, studentList);

    }
}
