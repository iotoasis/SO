package com.pineone.icbms.so.resources.activity;

import java.util.List;

import com.pineone.icbms.so.resources.action.IGenericAction;
import com.pineone.icbms.so.resources.property.operation.IGenericOperationValue;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;
import com.pineone.icbms.so.resources.result.IGenericResult;

/**
 * Generic activity interface.<BR/>
 * Created by uni4love on 2015. 06. 18..
 */
public interface IGenericActivity extends IActivity
{
	/**
	 * return action list.<BR/>
	 * 
	 * @return GENERIC_ACTION list
	 */
	List<IGenericAction> getActionList();

	/**
	 * return result.<BR/>
	 *
	 * @return IGenericResult
	 */
	IGenericResult getResult();

	/**
	 * return ontology reference.<BR/>
	 * 
	 * @return ontology reference
	 */
	IGenericOntologyReference getOntologyReference();

	/**
	 * return operation value.<BR/>
	 * 
	 * @return operation value
	 */
	IGenericOperationValue getOperationValue();
}
