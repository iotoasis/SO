package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.DeviceInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * get Specific Device Information with OntologyReference<BR/>
 * Created by Melvin on 2016. 1. 12..
 */
public class MakeDeviceInfoWithOntologyRefAction extends DefaultAction {

    private final Logger log = LoggerFactory.getLogger(MakeDeviceInfoWithOntologyRefAction.class);

    DefaultLocation location ;

    DefaultOntologyReference ontologyReference ;

    @Override
    public void execute(IGenericContext context) {

        DeviceInfoProvider deviceInfoProvider = new DeviceInfoProvider();

        /**
         * get Ontology Reference<BR/>
         */

        ontologyReference = (DefaultOntologyReference) context.getValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE);
        ontologyReference.getReference();
        ontologyReference.setName(ontologyReference.getReference());

        location = (DefaultLocation) context.getValue(IotServiceContext.ACTION_TARGET_LOCATION);

        /**
         * Use deviceInfoProvider and OntologyReference that SDA Interface(get Device List)<BR/>
         * add Information of Device to ACTION_DEVICE_URI<BR/>
         */
        List<DefaultPhysicalDevice> deviceList;

        deviceList = deviceInfoProvider.getDeviceListFromSDA(ontologyReference, location);

        for(DefaultPhysicalDevice device : deviceList){
            log.info(" >> deviceList : " + device.getUri());
        }

        context.addValue(IotServiceContext.ACTION_DEVICE_URI, deviceList);



    }
}


