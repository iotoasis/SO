package com.pineone.icbms.so.interfaces.database.controller.inputdata;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */

//외부로부터 입력받을 CVO 데이터 형태 정의
public class CompositeVirtualObjectData {

    String name;
    String functionality_id;
    String aspect_id;
    List<String> virtualObjectIdList;
    String type;
    String description;

    public CompositeVirtualObjectData() {
    }

    public List<String> getVirtualObjectIdList() {
        return virtualObjectIdList;
    }

    public void setVirtualObjectIdList(List<String> virtualObjectIdList) {
        this.virtualObjectIdList = virtualObjectIdList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctionality_id() {
        return functionality_id;
    }

    public void setFunctionality_id(String functionality_id) {
        this.functionality_id = functionality_id;
    }

    public String getAspect_id() {
        return aspect_id;
    }

    public void setAspect_id(String aspect_id) {
        this.aspect_id = aspect_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
