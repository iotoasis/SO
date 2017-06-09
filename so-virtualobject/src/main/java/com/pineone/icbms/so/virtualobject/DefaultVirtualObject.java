package com.pineone.icbms.so.virtualobject;

/**
 * Virtual Object default class.<BR/>
 * Created by uni4love on 2016. 11. 16..
 */
public class DefaultVirtualObject extends AGenericVirtualObject {
    /**
     * constructor
     */
    public DefaultVirtualObject() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     * @param name name
     */
    public DefaultVirtualObject(String id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append(super.toString()).append("]");
        return sb.toString();
    }
}
