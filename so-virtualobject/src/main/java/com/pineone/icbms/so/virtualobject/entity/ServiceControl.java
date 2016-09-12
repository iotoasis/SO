package com.pineone.icbms.so.virtualobject.entity;

public class ServiceControl {
    //
    private String domain;
    private String voService;
    private String operation;

    public ServiceControl() {
    }

    public ServiceControl(String domain, String voService, String operation) {
        this.domain = domain;
        this.voService = voService;
        this.operation = operation;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getVoService() {
        return voService;
    }

    public void setVoService(String voService) {
        this.voService = voService;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "ServiceControl{" +
                "domain='" + domain + '\'' +
                ", voService='" + voService + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
