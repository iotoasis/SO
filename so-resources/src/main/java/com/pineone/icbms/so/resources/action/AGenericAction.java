package com.pineone.icbms.so.resources.action;

import com.pineone.icbms.so.resources.result.IGenericResult;

/**
 * Generic action abstract class.<BR/>
 * Created by uni4love on 2015. 11. 01..
 */
abstract public class AGenericAction implements IGenericAction
{
    /**
     * id
     */
    protected long id;

    /**
     * name
     */
    protected String name;

    /**
     * result
     */
    protected IGenericResult result;

    @Override
    public Long getId()
    {
        return id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public IGenericResult getResult()
    {
        return result;
    }
}
