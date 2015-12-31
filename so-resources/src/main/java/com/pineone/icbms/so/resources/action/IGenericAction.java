package com.pineone.icbms.so.resources.action;

import com.pineone.icbms.so.resources.result.IGenericResult;

/**
 * Generic action interface.<BR/>
 * Created by uni4love on 2015. 06. 18..
 */
public interface IGenericAction extends IAction<Long, String>
{
    /**
     * execute action content.<BR/>
     * must defined input/output parameters.
     */
    void execute();

    /**
     * return result.<BR/>
     * @return result
     */
    IGenericResult getResult();
}
