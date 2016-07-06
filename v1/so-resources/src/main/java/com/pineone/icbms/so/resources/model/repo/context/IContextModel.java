package com.pineone.icbms.so.resources.model.repo.context;

import java.util.List;

import com.pineone.icbms.so.resources.model.IGenericModel;

/**
 * context model interface.<BR/>
 * Created by uni4love on 2015. 10. 15..
 */
public interface IContextModel<CONTEXT_MODEL extends IContextModel> extends IGenericModel
{
	/**
	 * type: context
	 */
	String TYPE = "so/resource/context";

	/**
	 * return context list.<BR/>
	 * 
	 * @return context list
	 */
	List<CONTEXT_MODEL> getContextModelList();
}
