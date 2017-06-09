package com.pineone.icbms.so.virtualobject.location;

import com.pineone.icbms.so.virtualobject.common.AGenericIdentity;

/**
 * abstract Generic location.<BR/>
 * Created by uni4love on 2016. 11. 27..
 */
abstract class AGenericLocation extends AGenericIdentity implements IGenericLocation {
    /**
     * location uri
     */
    String uri;

    /**
     * constructor<BR/>
     */
    public AGenericLocation() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public AGenericLocation(String id) {
        super(id);
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     * @param name name
     */
    public AGenericLocation(String id, String name) {
        this(id);
        this.name = name;

    }

    @Override
    public String getUri() {
        return uri;
    }

    /**
     * set location uri.<BR/>
     * @param uri uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(", uri: ").append(uri);
        return sb.toString();
    }
}
