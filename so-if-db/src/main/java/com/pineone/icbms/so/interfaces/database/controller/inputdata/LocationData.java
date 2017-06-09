package com.pineone.icbms.so.interfaces.database.controller.inputdata;

/**
 * Created by melvin on 2017. 3. 29..
 */
public class LocationData {

    String name;
    String uri;
    String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocationData(String name, String uri, String description) {
        this.name = name;
        this.uri = uri;
        this.description = description;
    }
}
