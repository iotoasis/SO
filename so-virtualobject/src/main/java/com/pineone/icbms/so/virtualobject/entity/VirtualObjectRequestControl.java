package com.pineone.icbms.so.virtualobject.entity;

public class VirtualObjectRequestControl {
    //
    private String voId;
    private String operation;

    public VirtualObjectRequestControl() {
    }

    public VirtualObjectRequestControl(String voId, String operation) {
        this.voId = voId;
        this.operation = operation;
    }

    public String getVoId() {
        return voId;
    }

    public void setVoId(String voId) {
        this.voId = voId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
