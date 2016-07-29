package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.PersonInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Melvin on 2016. 1. 13..
 * get Manager Information of Specific Location <BR/>
 */
public class MakeManagerInfoAction extends DefaultAction {

    private final Logger log = LoggerFactory.getLogger(MakeManagerInfoAction.class);

    DefaultLocation location ;

    @Override
    public void execute(IGenericContext context) {

        PersonInfoProvider personInfoProvider = new PersonInfoProvider();

        /**
         * Use PersonInfoProvider that has SDA Interface(get Manager Info) <BR/>
         * add Information of  Manager to ACTION_STUDENT_MANAGER  <BR/>
         */

        location = (DefaultLocation) context.getValue(IotServiceContext.ACTION_TARGET_LOCATION);

        List<DefaultStudent> studentList= personInfoProvider.getManagerInfoFromSDA(location);

        for(DefaultStudent student : studentList){
            log.info(" >> ManagerList : " + student.getUri());
        }

        context.addValue(IotServiceContext.ACTION_STUDENT_MANAGER, studentList);
    }
}
