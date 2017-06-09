package com.pineone.icbms.so.virtualobject.profile;


/**
 * Profile default class.<BR/>
 * Created by uni4love on 2016. 11. 17..
 */
public class DefaultProfile extends AGenericProfile {
    /**
     * constructor
     */
    public DefaultProfile() {
    }

    /**
     * constructor.<BR/>
     *
     * @param id ID
     */
    public DefaultProfile(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor
     *
     * @param id   id
     * @param name name
     */
    public DefaultProfile(String id, String name) {
        this(id);
        this.name = name;
    }
}
