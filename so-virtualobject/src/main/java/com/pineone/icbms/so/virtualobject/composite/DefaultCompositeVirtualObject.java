package com.pineone.icbms.so.virtualobject.composite;

/**
 * Composite Virtual Object default class.<BR/>
 * <p>
 * Created by uni4love on 2016. 11. 17..
 */
public class DefaultCompositeVirtualObject extends AGenericCompositeVirtualObject {
    /**
     * constructor<BR/>
     */
    public DefaultCompositeVirtualObject() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public DefaultCompositeVirtualObject(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public DefaultCompositeVirtualObject(String id, String name) {
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
    public DefaultCompositeVirtualObject(String id, String name, String description) {
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
