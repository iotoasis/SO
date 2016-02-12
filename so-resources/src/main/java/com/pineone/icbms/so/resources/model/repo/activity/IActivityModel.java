package com.pineone.icbms.so.resources.model.repo.activity;

import java.util.List;

import com.pineone.icbms.so.resources.model.IGenericModel;
import com.pineone.icbms.so.resources.model.repo.action.IActionModel;
import com.pineone.icbms.so.resources.property.operation.IGenericOperationValue;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * DefaultActivity model interface for repository.<BR/>
 * Created by uni4love on 2015. 10. 15..
 */
public interface IActivityModel<ACTION_MODEL extends IActionModel, OPERATION_VALUE extends IGenericOperationValue, ONTOLOGY_REFERENCE extends IGenericOntologyReference>
		extends IGenericModel
{
	/**
	 * type: activity
	 */
	String TYPE = "so/resource/activity";

	/**
	 * return Action model list.<BR/>
	 * 
	 * @return action model list
	 */
	List<ACTION_MODEL> getActionModelList();

	/**
	 * return Operation value.<BR/>
	 * 
	 * @return operation value
	 */
	OPERATION_VALUE getOperationValue();

	/**
	 * return Ontology reference.<BR/>
	 * 
	 * @return ontology reference
	 */
	ONTOLOGY_REFERENCE getOntologyReference();
}
