package com.pineone.icbms.so.service.entity;

import java.util.List;

public class SDAData<T> {
    String cmd;
    List<T> contents;
    String time;
    String contextId;


    public SDAData() {
    }

    public SDAData(String cmd, List<T> contents, String time, String contextId) {
        this.cmd = cmd;
        this.contents = contents;
        this.time = time;
        this.contextId = contextId;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public List<T> getContents() {
        return contents;
    }

    public void setContents(List<T> contents) {
        this.contents = contents;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    @Override
    public String toString() {
        return "SDAData{" +
                "cmd='" + cmd + '\'' +
                ", contents=" + contents +
                ", time='" + time + '\'' +
                ", contextId='" + contextId + '\'' +
                '}';
    }
}
