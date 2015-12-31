package com.pineone.icbms.so.resources.model.repo.profile;

import com.pineone.icbms.so.resources.model.repo.context.IContextModel;
import com.pineone.icbms.so.resources.model.repo.service.IServiceModel;
import com.pineone.icbms.so.resources.vo.IVirtualObject;

import java.util.List;

/**
 * Profile interface.<BR/>
 * Created by uni4love on 2015. 10. 02..
 */
public interface IProfile extends IVirtualObject<Long, String>
{
    /**
     * return context model list.<BR/>
     * @return context model list
     */
    List<IContextModel> getcontextModelList();

    /**
     * return service model list.<BR/>
     * @return service model list
     */
    List<IServiceModel> getServiceModelList();
}
