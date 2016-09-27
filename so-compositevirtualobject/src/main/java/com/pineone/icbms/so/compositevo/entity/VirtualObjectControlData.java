package com.pineone.icbms.so.compositevo.entity;

/**
 * Virtual Object의 제어를 위한 데이터
 */
public class VirtualObjectControlData {
    //

    /**
     * Virtual Object의 식별자
     * format : vo-(devicename)-(service)
     * ex)  vo-ventilator01-001-power-control
     */
    private String id;

    /**
     * Virtual Object의 서비스 위치
     * format : free
     * ex)  ITBT_606_001
     */
    private String domain;

    /**
     * Virtual Object의 서비스
     * format : icbms-voservice-(devicename)-(service)
     * ex) icbms-voservice-ventilator-power-control
     */
    private String functionality;

    /**
     * Virtual Object의 제어 명령어
     * format : Device Command
     * ex) ON
     */
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
