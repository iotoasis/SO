package com.pineone.icbms.so.creator.service;

/**
 * Created by melvin on 2016. 10. 6..
 */
public class ServiceUnit {

    private String generalObjectId;
    private String functionality;
    private String operation;

    public String getGeneralObjectId() {
        return generalObjectId;
    }

    public void setGeneralObjectId(String generalObjectId) {
        this.generalObjectId = generalObjectId;
    }

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "ServiceUnit{" +
                "generalObjectId='" + generalObjectId + '\'' +
                ", functionality='" + functionality + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
