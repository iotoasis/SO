package com.pineone.icbms.so.virtualobject.context.contextmodel;

/**
 * ContextModel default class.<BR/>
 *
 * Created by uni4love on 2016. 11. 31..
 */
public class DefaultContextModel extends AGenericContextModel {
    /**
     * constructor
     */
    public DefaultContextModel() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public DefaultContextModel(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public DefaultContextModel(String id, String name) {
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
    public DefaultContextModel(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append(super.toString()).append("]");
        return sb.toString();
    }
}
