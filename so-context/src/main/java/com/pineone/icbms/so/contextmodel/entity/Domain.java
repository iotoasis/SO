package com.pineone.icbms.so.contextmodel.entity;


/**
 * Created by melvin on 2016. 8. 1..
 */
public class Domain {

    String name;
    String uri;

    public Domain(String name, String uri){
        this.name = name;
        this.uri = uri;
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
