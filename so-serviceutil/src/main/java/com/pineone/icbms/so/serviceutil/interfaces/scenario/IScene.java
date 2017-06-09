package com.pineone.icbms.so.serviceutil.interfaces.scenario;

import com.pineone.icbms.so.virtualobject.common.IGenericIdNameOwner;

import java.util.List;

/**
 * scene interface.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 12..
 */
public interface IScene<T extends ICut> extends IGenericIdNameOwner {
    /**
     * return cut list.<BR/>
     *
     * @return List<ICut>
     */
    List<ICut> getCutList();
}
