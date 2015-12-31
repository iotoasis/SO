package com.pineone.icbms.so.iot.resources.message;

import com.pineone.icbms.so.resources.domain.IDomain;
import com.pineone.icbms.so.resources.property.IOntologyReference;
import com.pineone.icbms.so.resources.property.IValue;

/**
 * Virtual device control message abstract class.<BR/>
 * Created by uni4love on 2015. 10. 10..
 */
abstract public class AGenericVirtualDeviceControlMessage
		implements IGenericVirtualDeviceControlMessage
{
	/**
	 * domain
	 */
	private IDomain domain;

	/**
	 * ontology reference
	 */
	private IOntologyReference ontologyRef;

	/**
	 * value
	 */
	private IValue value;
}
