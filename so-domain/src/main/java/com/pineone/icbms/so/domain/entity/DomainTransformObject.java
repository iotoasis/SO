package com.pineone.icbms.so.domain.entity;

/**
 * Created by melvin on 2016. 8. 22..
 * NOTE: 외부에 노출될 Domain Data - Presentation, Controller 에서 사용
 */
public class DomainTransformObject {

    String id;
    String name;
    String uri;

    public DomainTransformObject() {
    }

    public DomainTransformObject(String id, String name, String uri) {
        this.id = id;
        this.name = name;
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
