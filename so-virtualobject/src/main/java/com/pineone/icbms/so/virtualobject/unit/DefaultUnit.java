package com.pineone.icbms.so.virtualobject.unit;

/**
 * Unit default class.<BR/>
 * <p>
 * Created by uni4love on 2016. 11. 27..
 */
public class DefaultUnit extends AGenericUnit {
    /**
     * constructor<BR/>
     */
    public DefaultUnit() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public DefaultUnit(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public DefaultUnit(String id, String name) {
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
    public DefaultUnit(String id, String name, String description) {
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
