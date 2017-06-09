package com.pineone.icbms.so.interfaces.database.controller.inputdata;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
public class OrchestrationServiceData {

    String name;
    List<String> virtualObjectIdList;
    List<String> compositeVirtualObjectIdList;
    String description;
    String parent_id;

    public OrchestrationServiceData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getVirtualObjectIdList() {
        return virtualObjectIdList;
    }

    public void setVirtualObjectIdList(List<String> virtualObjectIdList) {
        this.virtualObjectIdList = virtualObjectIdList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public List<String> getCompositeVirtualObjectIdList() {
        return compositeVirtualObjectIdList;
    }

    public void setCompositeVirtualObjectIdList(List<String> compositeVirtualObjectIdList) {
        this.compositeVirtualObjectIdList = compositeVirtualObjectIdList;
    }
}
