package com.pineone.icbms.so.resources.property.operation;

import com.pineone.icbms.so.resources.vo.AGenericVirtualObject;

/**
 * Generic operation value abstract class.<BR/>
 * Created by uni4love on 2016. 1. 12..
 */
abstract public class AGenericOperationValue extends AGenericVirtualObject
		implements IGenericOperationValue
{
	/**
	 * operation value
	 */
	protected String operationValue = null;

	@Override
	public String getOperationValue()
	{
		return operationValue;
	}

	public void setOperationValue(String operationValue) {
		this.operationValue = operationValue;
	}
}
