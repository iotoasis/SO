package com.pineone.icbms.so.resources.model.repo.profile;

import java.util.List;

import com.pineone.icbms.so.resources.context.IContext;
import com.pineone.icbms.so.resources.model.IGenericModel;
import com.pineone.icbms.so.resources.model.repo.context.IContextModel;
import com.pineone.icbms.so.resources.model.repo.service.IServiceModel;

/**
 * Profile model interface.<BR/>
 * Created by uni4love on 2015. 10. 02..
 */
public interface IProfileModel<CONTEXT_MODEL extends IContextModel, SERVICE_MODEL extends IServiceModel>
		extends IGenericModel
{
	/**
	 * type: context
	 */
	String TYPE = "so/resource/profile";

	/**
	 * return context model list.<BR/>
	 * 
	 * @return context model list
	 */
	List<CONTEXT_MODEL> getContextModelList();

	/**
	 * return service model list.<BR/>
	 * 
	 * @return service model list
	 */
	List<SERVICE_MODEL> getServiceModelList();

	/**
	 * return domain.<BR/>
	 * 
	 * @return domain
	 */
	String getDomain();
}
