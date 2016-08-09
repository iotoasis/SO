package com.pineone.icbms.so.resources.model.repo.context;

import java.util.List;

import com.pineone.icbms.so.resources.model.AGenericModel;

/**
 * Default context model class.<BR/>
 * Created by Melvin on 15. 10. 21..
 */
public class DefaultContextModel extends AGenericModel
		implements IContextModel<DefaultContextModel>
{
	/**
	 * context model list
	 */
	protected List<DefaultContextModel> contextModelList = null;

	@Override
	public String getType()
	{
		return TYPE;
	}

	/**
	 * add a context model.<BR/>
	 * 
	 * @param contextModel
	 *            context model
	 */
	public void addContextModel(DefaultContextModel contextModel)
	{
		this.contextModelList.add(contextModel);
	}

	@Override
	public List<DefaultContextModel> getContextModelList()
	{
		return contextModelList;
	}
}
