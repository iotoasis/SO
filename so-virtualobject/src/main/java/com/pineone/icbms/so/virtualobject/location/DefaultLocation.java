package com.pineone.icbms.so.virtualobject.location;

/**
 * Location default class.<BR/>
 * <p>
 * Created by uni4love on 2016. 11. 27..
 */
public class DefaultLocation extends AGenericLocation {
    /**
     * constructor<BR/>
     */
    public DefaultLocation() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public DefaultLocation(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public DefaultLocation(String id, String name) {
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
    public DefaultLocation(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public DefaultLocation(String id, String name, String description, String uri) {
        this(id, name, description);
        this.uri = uri;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append(super.toString()).append("]");
        return sb.toString();
    }
}
