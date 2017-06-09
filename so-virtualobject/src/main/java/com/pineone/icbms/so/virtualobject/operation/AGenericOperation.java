package com.pineone.icbms.so.virtualobject.operation;

import com.pineone.icbms.so.virtualobject.common.AGenericIdentity;

/**
 * abstract Generic location.<BR/>
 * Created by uni4love on 2016. 11. 27..
 */
abstract class AGenericOperation extends AGenericIdentity implements IGenericOperation {
    /**
     * location uri
     */
    protected String uri;

    /**
     * constructor
     */
    public AGenericOperation() {
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     */
    public AGenericOperation(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public AGenericOperation(String id, String name) {
        this(id);
        this.name = name;
    }

    /**
     * constructor<BR/>
     *
     * @param id          id
     * @param name        name
     * @param description description
     */
    public AGenericOperation(String id, String name, String description) {
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
