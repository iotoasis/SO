package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * SmartSwitch device driver.<BR/>
 * Created by use on 2015-10-29.
 */
public abstract class ASmartSwitchDeviceDriver extends DefaultDeviceDriver {

    /**
     * SmartSwitch execute
     * @param context
     * @return
     */
    @Override
    public String execute(IGenericDeviceContext context)
    {
        IGenericOntologyReference ontologyReference = context
                .getOntologyReference();
        String result = "";

        switch (ontologyReference.getId())
        {
            case IGenericOntologyReference.REF_DEHUMIDIFY_CONTROL:
                result = smartSwitchControl(context);
                break;
        }
        return result;
    }

    abstract String smartSwitchControl(IGenericDeviceContext context);
}
