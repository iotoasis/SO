package com.pineone.icbms.so.virtualobject.common;


import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * abstract Generic id-name owner.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
abstract public class AGenericIdNameOwner implements IGenericIdNameOwner {
    /**
     * id
     */
    protected String id;

    /**
     * name
     */
    protected String name;

    /**
     * constuctor
     */
    public AGenericIdNameOwner() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public AGenericIdNameOwner(String id) {
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     * @param name name
     */
    public AGenericIdNameOwner(String id, String name) {
        this(id);
        this.name = name;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("id: ").append(id);
        sb.append(", name: ").append(name);
        return sb.toString();
    }
}
