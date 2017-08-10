package com.pineone.icbms.so.virtualobject.function;

import com.pineone.icbms.so.virtualobject.common.AGenericIdentity;

/**
 * abstract Generic Function.<BR/>
 * Created by uni4love on 2016. 11. 27..
 * so 의 function는 sda의 function 임
 */
abstract class AGenericFunction extends AGenericIdentity implements IGenericFunction {
    /**
     * location uri
     */
    protected String uri;

    /**
     * constructor<BR/>
     */
    public AGenericFunction() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public AGenericFunction(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public AGenericFunction(String id, String name) {
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
    public AGenericFunction(String id, String name, String description) {
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
    public AGenericFunction(String id, String name, String description, String uri) {
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
