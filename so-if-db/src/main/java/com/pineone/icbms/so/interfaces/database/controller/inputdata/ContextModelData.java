package com.pineone.icbms.so.interfaces.database.controller.inputdata;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */

// 외부로 부터 등록받을 ContextModel 데이터
public class ContextModelData {
    //
    String name;
    List<String> contextInformationIdList;
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getContextInformationIdList() {
        return contextInformationIdList;
    }

    public void setContextInformationIdList(List<String> contextInformationIdList) {
        this.contextInformationIdList = contextInformationIdList;
    }
}
