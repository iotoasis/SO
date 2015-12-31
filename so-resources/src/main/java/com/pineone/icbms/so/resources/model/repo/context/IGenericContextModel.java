package com.pineone.icbms.so.resources.model.repo.context;

import java.util.List;

import com.pineone.icbms.so.resources.context.IGenericContext;

/**
 * generic context model interface.<BR/>
 * Created by uni4love on 2015. 12. 16..
 */
public interface IGenericContextModel<String, V>
		extends IContextModel<Long, String>
{
	/**
	 * return context list.<BR/>
	 * 
	 * @return context list
	 */
	List<IGenericContext> getContextList();
}
