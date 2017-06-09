package com.pineone.icbms.so.interfaces.sda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pineone.icbms.so.virtualobject.common.AGenericIdNameOwner;

import java.util.List;

/**
 * ContextModelForIf model class for SDA interface.<BR/>
 *
 * Created by uni4love on 2017. 1. 19..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContextModelForIf extends AGenericIdNameOwner {
    /**
     * uri
     */
    String uri;

    /**
     * context information list
     */
    protected List<ContextInformationForIf> contextInformationList;

    /**
     * contructor
     */
    public ContextModelForIf() {
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public ContextModelForIf(String id, String name) {
        super(id, name);
    }

    /**
     * return Context Information list.<BR/>
     *
     * @return Context Information list
     */
    public List<ContextInformationForIf> getContextInformationList() {
        return contextInformationList;
    }

    public void setContextInformationList(List<ContextInformationForIf> contextInformationList) {
        this.contextInformationList = contextInformationList;
    }

    public void addContextInformation(ContextInformationForIf contextInformation) {
        this.contextInformationList.add(contextInformation);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[id = ").append(id);
        sb.append(", name = ").append(name);
        sb.append(", uri: ").append(uri);
        if (contextInformationList != null) {
            for (ContextInformationForIf ci : contextInformationList) {
                sb.append(ci);
            }
        }
        sb.capacity();
        sb.append("]");
        return sb.toString();
    }
}
