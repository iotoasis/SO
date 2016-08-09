package com.pineone.icbms.so.iot.resources.driver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.resources.vo.AGenericVirtualObject;

/**
 * Generic device driver abstract class.<BR/>
 * Created by uni4love on 2015. 10. 20..
 */
abstract public class AGenericDeviceDriver<RESULT, DEVICE_CONTEXT extends IGenericDeviceContext>
		extends AGenericVirtualObject
		implements IGenericDeviceDriver<RESULT, DEVICE_CONTEXT>
{
}
