package com.pineone.icbms.so.resources.activity;

import java.util.Iterator;
import java.util.List;

import com.pineone.icbms.so.resources.action.IGenericAction;
import com.pineone.icbms.so.resources.context.AGenericContext;
import com.pineone.icbms.so.resources.property.operation.IGenericOperationValue;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;
import com.pineone.icbms.so.resources.result.IGenericResult;

/**
 * generic activity abstract class.<BR/>
 * Created by uni4love on 2015. 06. 18..
 */
abstract public class AGenericActivity extends AGenericContext<Object>
		implements IGenericActivity, java.io.Serializable
{
	private static final long serialVersionUID = 5602102149406280798l;

	/**
	 * result
	 */
	protected IGenericResult result = null;

	/**
	 * operation value
	 */
	protected IGenericOperationValue operationValue = null;

	/**
	 * ontology reference
	 */
	protected IGenericOntologyReference ontologyReference = null;

	/**
	 * action list
	 */
	List<IGenericAction> actionList = null;

	/**
	 * constructor
	 */
	public AGenericActivity()
	{
		actionList = createActionList();
	}

	@Override
	public List<IGenericAction> getActionList()
	{
		return actionList;
	}

	public void setActionList(List<IGenericAction> actionList)
	{
		this.actionList = actionList;
	}

	@Override
	public IGenericResult getResult()
	{
		return result;
	}

	public void setResult(IGenericResult result)
	{
		this.result = result;
	}

	@Override
	public IGenericOntologyReference getOntologyReference()
	{
		return ontologyReference;
	}

	public void setOntologyReference(
			IGenericOntologyReference ontologyReference)
	{
		this.ontologyReference = ontologyReference;
	}

	@Override
	public IGenericOperationValue getOperationValue()
	{
		return operationValue;
	}

	public void setOperationValue(IGenericOperationValue operationValue)
	{
		this.operationValue = operationValue;
	}

	/**
	 * add a action.<BR/>
	 *
	 * @param action
	 *            action
	 */
	public void addAction(IGenericAction action)
	{
		this.actionList.add(action);
	}

	/**
	 * create and return IGenericAction list.<BR/>
	 * 
	 * @return List<IGenericAction>
	 */
	abstract protected List<IGenericAction> createActionList();

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer(">>> activity\n");
		sb.append(super.toString());
		sb.append("\n");
		Iterator iter = actionList.iterator();
		while (iter.hasNext())
		{
			sb.append(iter.next());
		}
		return sb.toString();
	}
}
