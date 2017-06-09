package com.pineone.icbms.so.virtualobject.context.contextinformation;

/**
 * ContextInformation default class.<BR/>
 *
 * Created by uni4love on 2016. 11. 26..
 */
public class DefaultContextInformation extends AGenericContextInformation {
    /**
     * constructor
     */
    public DefaultContextInformation() {
    }

    /**
     * constructor
     *
     * @param id id
     */
    public DefaultContextInformation(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     * @param name name
     */
    public DefaultContextInformation(String id, String name) {
        this(id);
        this.name = name;
    }

    public DefaultContextInformation(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    public DefaultContextInformation(String id, String name, String description, String uri) {
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
