package com.pineone.icbms.so.service.proxy;

import java.util.List;

public class RequestData {
    //
    private String cmd;
    private String contextId;
    private String time;
    private List<LackEquipment> contents;

    public RequestData() {
    }

    public RequestData(String cmd, String contextId, String time, List<LackEquipment> contents) {
        this.cmd = cmd;
        this.contextId = contextId;
        this.time = time;
        this.contents = contents;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<LackEquipment> getContents() {
        return contents;
    }

    public void setContents(List<LackEquipment> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "cmd='" + cmd + '\'' +
                ", contextId='" + contextId + '\'' +
                ", time='" + time + '\'' +
                ", contents=" + contents +
                '}';
    }
}
