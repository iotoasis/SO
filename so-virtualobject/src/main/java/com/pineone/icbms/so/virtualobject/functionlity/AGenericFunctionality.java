package com.pineone.icbms.so.virtualobject.functionlity;

import com.pineone.icbms.so.virtualobject.common.AGenericIdentity;

/**
 * abstract Generic Functionality.<BR/>
 * Created by uni4love on 2016. 11. 27..
 */
abstract class AGenericFunctionality extends AGenericIdentity implements IGenericFunctionality {
    /**
     * location uri
     */
    protected String uri;

    /**
     * constructor<BR/>
     */
    public AGenericFunctionality() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public AGenericFunctionality(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public AGenericFunctionality(String id, String name) {
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
    public AGenericFunctionality(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    /**
     * constructor<BR/>
     *
     * @param id          id
     * @param name        name
     * @param description description
     * @param uri         uri
     */
    public AGenericFunctionality(String id, String name, String description, String uri) {
        this(id, name, description);
        this.uri = uri;
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
