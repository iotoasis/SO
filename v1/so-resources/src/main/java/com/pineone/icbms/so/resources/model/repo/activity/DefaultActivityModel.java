package com.pineone.icbms.so.resources.model.repo.activity;

import com.pineone.icbms.so.resources.model.AGenericModel;
import com.pineone.icbms.so.resources.model.repo.action.DefaultActionModel;
import com.pineone.icbms.so.resources.property.operation.DefaultOperationValue;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;

import java.util.List;

/**
 * Activity model default class.<BR/>
 * Created by uni4love on 2015. 11. 4..
 */
public class DefaultActivityModel extends AGenericModel implements
		IActivityModel<DefaultActionModel, DefaultOperationValue, DefaultOntologyReference>
{
	/**
	 * action model list
	 */
	protected List<DefaultActionModel> actionModelList = null;

	/**
	 * operation Value
	 */
	protected DefaultOperationValue operationValue = null;

	/**
	 * ontology reference
	 */
	protected DefaultOntologyReference ontologyReference = null;

	@Override
	public String getType()
	{
		return TYPE;
	}

	@Override
	public List<DefaultActionModel> getActionModelList()
	{
		return actionModelList;
	}

	public void setActionModelList(List<DefaultActionModel> actionModelList)
	{
		this.actionModelList = actionModelList;
	}

	@Override
	public DefaultOperationValue getOperationValue()
	{
		return operationValue;
	}

	public void setOperationValue(DefaultOperationValue operationValue)
	{
		this.operationValue = operationValue;
	}

	@Override
	public DefaultOntologyReference getOntologyReference()
	{
		return ontologyReference;
	}

	public void setOntologyReference(DefaultOntologyReference ontologyReference)
	{
		this.ontologyReference = ontologyReference;
	}
}
