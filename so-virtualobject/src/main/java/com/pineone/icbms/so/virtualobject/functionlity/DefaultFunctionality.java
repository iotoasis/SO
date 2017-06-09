package com.pineone.icbms.so.virtualobject.functionlity;

/**
 * Functionality default class.<BR/>
 *
 * Created by Melvin on 2016. 11. 27..
 */
public class DefaultFunctionality extends AGenericFunctionality {
    /**
     * constructor<BR/>
     */
    public DefaultFunctionality() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public DefaultFunctionality(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public DefaultFunctionality(String id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * constructor<BR/>
     *
     * @param id          id
     * @param name        name
     * @param description description
     */
    public DefaultFunctionality(String id, String name, String description) {
        super(id, name);
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
    public DefaultFunctionality(String id, String name, String description, String uri) {
        super(id, name, description);
        this.uri = uri;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append(super.toString()).append("]");
        return sb.toString();
    }
}
