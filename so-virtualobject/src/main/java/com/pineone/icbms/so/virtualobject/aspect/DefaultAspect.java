package com.pineone.icbms.so.virtualobject.aspect;

/**
 * Aspect default class.<BR/>
 * <p>
 * Created by uni4love on 2016. 11. 27..
 */
public class DefaultAspect extends AGenericAspect {
    /**
     * constructor
     */
    public DefaultAspect() {
    }

    /**
     * constructor
     *
     * @param id
     */
    public DefaultAspect(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public DefaultAspect(String id, String name) {
        super(id, name);
    }

    /**
     * constructor
     *
     * @param description description
     */
    public DefaultAspect(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    /**
     * constructor
     *
     * @param id          id
     * @param name        name
     * @param description description
     * @param uri         uri
     */
    public DefaultAspect(String id, String name, String description, String uri) {
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
