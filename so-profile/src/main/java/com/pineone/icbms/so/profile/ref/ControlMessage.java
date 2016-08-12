package com.pineone.icbms.so.profile.ref;

/**
 * Created by melvin on 2016. 8. 11..
 */
public class ControlMessage {

    String thingName;
    String operation;
    String domainName;

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
