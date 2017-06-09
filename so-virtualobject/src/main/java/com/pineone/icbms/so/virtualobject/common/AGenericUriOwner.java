package com.pineone.icbms.so.virtualobject.common;

/**
 * abstract Generic id-name owner.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
abstract public class AGenericUriOwner implements IGenericUriOwner {
    /**
     * uri
     */
    protected String uri = null;

    @Override
    public String getUri() {
        return this.uri;
    }

    public void setId(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(", uri: ").append(uri);
        return sb.toString();
    }
}
