package com.pineone.icbms.so.interfaces.messagequeue.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by uni4love on 2017. 5. 1..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
public class FunctionForMQ extends UriOwnerForMQ {
    /**
     * constructor
     */
    public FunctionForMQ() {
    }

    /**
     * constructor
     *
     * @param id          id
     */
    public FunctionForMQ(String id) {
        this();
        this.id = id;
    }
    /**
     * constructor
     *
     * @param id          id
     * @param name        name
     */
    public FunctionForMQ(String id, String name) {
        this(id);
        this.name = name;
    }

    /**
     * constructor
     *
     * @param id          id
     * @param name        name
     * @param description description
     */
    public FunctionForMQ(String id, String name, String description) {
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
    public FunctionForMQ(String id, String name, String description, String uri) {
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
