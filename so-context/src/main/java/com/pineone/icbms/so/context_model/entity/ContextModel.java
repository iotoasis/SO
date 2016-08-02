package com.pineone.icbms.so.context_model.entity;

import com.pineone.icbms.so.context_information.entity.ContextInformation;
import java.util.List;

/**
 * Created by melvin on 2016. 7. 20..
 */
public class ContextModel {

    private String name;
    private List<Domain> domainList;
    private List<ContextInformation> contextInformationList;
    private String contextType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Domain> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<Domain> domainList) {
        this.domainList = domainList;
    }

    public List<ContextInformation> getContextInformationList() {
        return contextInformationList;
    }

    public void setContextInformationList(List<ContextInformation> contextInformationList) {
        this.contextInformationList = contextInformationList;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }
}
