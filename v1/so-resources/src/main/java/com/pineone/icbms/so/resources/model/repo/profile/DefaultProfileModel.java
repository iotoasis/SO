package com.pineone.icbms.so.resources.model.repo.profile;

import com.pineone.icbms.so.resources.model.AGenericModel;
import com.pineone.icbms.so.resources.model.repo.context.DefaultContextModel;
import com.pineone.icbms.so.resources.model.repo.service.DefaultServiceModel;

import java.util.List;

/**
 * Profile model default class.<BR/>
 * Created by uni4love on 2015. 11. 8..
 */
public class DefaultProfileModel extends AGenericModel
		implements IProfileModel<DefaultContextModel, DefaultServiceModel>
{
	/**
	 * context model list
	 */
	protected List<DefaultContextModel> contextModelList = null;

	/**
	 * service model list
	 */
	protected List<DefaultServiceModel> serviceModelList = null;

	/**
	 * domain
	 */
	protected String domain = null;

	@Override
	public String getType()
	{
		return TYPE;
	}

	@Override
	public List<DefaultContextModel> getContextModelList()
	{
		return contextModelList;
	}

	@Override
	public List<DefaultServiceModel> getServiceModelList()
	{
		return serviceModelList;
	}

	@Override
	public String getDomain()
	{
		return domain;
	}
}
