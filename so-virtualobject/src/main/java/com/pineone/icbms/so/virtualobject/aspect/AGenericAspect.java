package com.pineone.icbms.so.virtualobject.aspect;

import com.pineone.icbms.so.virtualobject.common.AGenericIdentity;

/**
 * abstract Generic Aspect.<BR/>
 *
 * Created by uni4love on 2016. 11. 27..
 */
abstract class AGenericAspect extends AGenericIdentity implements IGenericAspect {
    /**
     * location uri
     */
    protected String uri;

    /**
     * constructor
     */
    public AGenericAspect() {
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     */
    public AGenericAspect(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public AGenericAspect(String id, String name) {
        this(id);
        this.name = name;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public AGenericAspect(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    @Override
    public String getUri() {
        return uri;
    }

    /**
     * set location uri.<BR/>
     *
     * @param uri uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(", uri = ").append(uri);
        return sb.toString();
    }
}
