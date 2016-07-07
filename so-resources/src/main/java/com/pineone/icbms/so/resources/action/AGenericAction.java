package com.pineone.icbms.so.resources.action;

import com.pineone.icbms.so.resources.result.IGenericResult;
import com.pineone.icbms.so.resources.vo.AGenericVirtualObject;

/**
 * Generic action abstract class.<BR/>
 * Created by uni4love on 2015. 11. 01..
 */
abstract public class AGenericAction extends AGenericVirtualObject
		implements IGenericAction, java.io.Serializable
{
	private static final long serialVersionUID = -4250441849269071769l;

	/**
	 * result
	 */
	protected IGenericResult result;

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
	public String toString()
	{
		StringBuffer sb = new StringBuffer(">>>> action");
		sb.append("\nid: ").append(id);
		sb.append("\nname: ").append(name);
		return sb.toString();
	}
}
