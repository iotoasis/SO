package com.pineone.icbms.so.resources.property.operation;

/**
 * Generic operation value interface.<BR/>
 * Created by uni4love on 2016. 1. 12..
 */
public interface IGenericOperationValue extends IOperationValue<String, String>
{
    /**
     * operation: ON
     */
    String OPERATIOIN_ON = "ON";

    /**
     * operation: OFF
     */
    String OPERATION_OFF = "OFF";

    /**
     * return operation value.<BR/>
     * @return operation value
     */
	String getOperationValue();
}
