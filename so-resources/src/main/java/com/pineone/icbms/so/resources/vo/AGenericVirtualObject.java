package com.pineone.icbms.so.resources.vo;

import java.io.Serializable;

/**
 * Generic virtual object.<BR/>
 * Created by uni4love on 2015. 10. 7..
 */
abstract public class AGenericVirtualObject implements IGenericVirtualObject, Serializable
{
    /**
     * id
     */
    protected String id = null;

    /**
     * name
     */
    protected String name = null;

    @Override
    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("\nid: ").append(id);
        sb.append("\nname: ").append(name);
        return sb.toString();
    }
}
