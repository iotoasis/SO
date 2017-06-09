package com.pineone.icbms.so.interfaces.messagequeue.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * ContextInformation model for MQ.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 5..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
public class ContextModelForMQ extends ACommonForMQ {
    /**
     * context model sid
     */
    String contextSid;

    /**
     * uri
     */
    String uri;

    /**
     * context information list
     */
    List<ContextInformationForMQ> contextInformationList;

    /**
     * location list
     */
    List<LocationForMQ> locationList;

    /**
     * constructor
     */
    public ContextModelForMQ() {
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public ContextModelForMQ(String id, String name) {
        super(id, name);
    }

    public String getContextSid() {
        return contextSid;
    }

    public void setContextSid(String contextSid) {
        this.contextSid = contextSid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<ContextInformationForMQ> getContextInformationList() {
        return contextInformationList;
    }

    public void setContextInformationList(List<ContextInformationForMQ> contextInformationList) {
        this.contextInformationList = contextInformationList;
    }

    public void addContextInformation(ContextInformationForMQ contextInformation) {
        if (contextInformationList == null)
            contextInformationList = new ArrayList<>();
        this.contextInformationList.add(contextInformation);
    }

    public List<LocationForMQ> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationForMQ> locationList) {
        this.locationList = locationList;
    }

    public void addLocation(LocationForMQ location) {
        if (locationList == null)
            this.locationList = new ArrayList<>();
        this.locationList.add(location);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append(super.toString())
                .append(", uri = ").append(uri);
        if (contextInformationList != null) {
            for (ContextInformationForMQ ci : contextInformationList) {
                sb.append(",\ncontextinformation: ").append(ci);
            }
        }
        if (locationList != null) {
            for (LocationForMQ location : locationList) {
                sb.append(", ").append(location);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
