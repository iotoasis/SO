package com.pineone.icbms.so.virtualobject.context.contextmodel;

import com.pineone.icbms.so.virtualobject.common.IGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.common.IUriOwner;
import com.pineone.icbms.so.virtualobject.context.contextinformation.IGenericContextInformation;

import java.util.List;

/**
 * generic context model interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
public interface IGenericContextModel extends IContextModel, IUriOwner<String>, IGenericServiceEntity {
    /**
     * return Context Information list.<BR/>
     *
     * @return Context Information list
     */
    List<IGenericContextInformation> getContextInformationList();
}
