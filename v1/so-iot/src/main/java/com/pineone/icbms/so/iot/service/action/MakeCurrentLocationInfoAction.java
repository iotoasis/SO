package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.LocationInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * get current Location of Specific Person<BR/>
 * Created by Melvin on 2016. 1. 13..
 */
public class MakeCurrentLocationInfoAction extends DefaultAction {

    private final Logger log = LoggerFactory.getLogger(MakeCurrentLocationInfoAction.class);

    DefaultStudent student;

    @Override
    public void execute(IGenericContext context) {

        LocationInfoProvider locationInfoProvider = new LocationInfoProvider();

        student = ((DefaultStudent)context.getValue(IotServiceContext.ACTION_TARGET_STUDENT));

        /**
         * Use LocationInfoProvider that has SDA Interface(get Current Location) <BR/>
         * add Current Location to ACTION_TARGET_LOCATION<BR/>
         */

        DefaultLocation location = locationInfoProvider.getCurrentLocationFromSDA(student);

        log.info(" >> Location : " + location.getUri());


        context.addValue(IotServiceContext.ACTION_TARGET_LOCATION, location);

    }
}
