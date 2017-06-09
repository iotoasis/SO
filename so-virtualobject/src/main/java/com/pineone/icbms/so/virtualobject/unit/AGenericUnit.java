package com.pineone.icbms.so.virtualobject.unit;

import com.pineone.icbms.so.virtualobject.common.AGenericIdentity;

/**
 * Unit abstract generic class.<BR/>
 * <p>
 * Created by uni4love on 2016. 11. 27..
 */
abstract class AGenericUnit extends AGenericIdentity implements IGenericUnit {
    /**
     * location uri
     */
    protected String uri;

    /**
     * constructor<BR/>
     */
    public AGenericUnit() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public AGenericUnit(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public AGenericUnit(String id, String name) {
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
    public AGenericUnit(String id, String name, String description) {
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
