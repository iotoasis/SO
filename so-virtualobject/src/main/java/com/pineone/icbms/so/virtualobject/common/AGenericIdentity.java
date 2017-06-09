package com.pineone.icbms.so.virtualobject.common;

/**
 * abstract Generic id-name owner.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
abstract public class AGenericIdentity extends AGenericIdNameOwner implements IGenericIdentity {
    /**
     * description
     */
    protected String description;

    /**
     * constuctor
     */
    public AGenericIdentity() {
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     */
    public AGenericIdentity(String id) {
        super(id);
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public AGenericIdentity(String id, String name) {
        this(id);
        this.name = name;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     * @param description description
     */
    public AGenericIdentity(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(", description: ").append(description);
        return sb.toString();
    }
}
