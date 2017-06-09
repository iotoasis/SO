package com.pineone.icbms.so.virtualobject.operation;

/**
 * Location default class.<BR/>
 * Created by Melvin on 2016. 11. 27..
 */
public class DefaultOperation extends AGenericOperation {
    /**
     * constructor<BR/>
     */
    public DefaultOperation() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public DefaultOperation(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     * @param name name
     */
    public DefaultOperation(String id, String name) {
        this(id);
        this.name = name;
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     * @param name name
     */
    public DefaultOperation(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append(super.toString()).append("]");
        return sb.toString();
    }
}
