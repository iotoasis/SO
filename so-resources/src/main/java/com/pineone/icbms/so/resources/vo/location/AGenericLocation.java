package com.pineone.icbms.so.resources.vo.location;

import com.pineone.icbms.so.resources.vo.AGenericVirtualObject;

/**
 * Generic location.<BR/>
 * Created by Melvin on 2015. 12. 7..
 */
abstract class AGenericLocation extends AGenericVirtualObject implements IGenericLocation
{
    protected String uri;

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append("uri: ").append(uri);
        return sb.toString();
    }
}
