package com.pineone.icbms.so.virtualobject.context.contextinformation;

import com.pineone.icbms.so.virtualobject.common.AGenericIdentity;

/**
 * generic context information abstract class.<BR/>
 *
 * Created by uni4love on 2016. 11. 26..
 */
abstract public class AGenericContextInformation extends AGenericIdentity implements IGenericContextInformation {
    /**
     * uri
     */
    protected String uri;

    /**
     * constructor
     */
    public AGenericContextInformation() {
    }

    @Override
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(", uri: ").append(uri);
        return sb.toString();
    }
}
