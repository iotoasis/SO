package com.pineone.icbms.so.compositevo.entity;

public class VirtualObjectControlData {
    //
    private String id;
    private String domain;
    private String functionality;
    private String operation;

    public VirtualObjectControlData() {
    }

    public VirtualObjectControlData(String id, String domain, String functionality, String operation) {
        this.id = id;
        this.domain = domain;
        this.functionality = functionality;
        this.operation = operation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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
        return "VirtualObjectControlData{" +
                "id='" + id + '\'' +
                ", domain='" + domain + '\'' +
                ", functionality='" + functionality + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
