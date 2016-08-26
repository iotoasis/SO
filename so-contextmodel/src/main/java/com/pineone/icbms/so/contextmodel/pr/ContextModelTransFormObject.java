package com.pineone.icbms.so.contextmodel.pr;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 22..
 * NOTE: 외부에 노출될 ContextModel Data - Presentation, Controller 에서 사용
 */
public class ContextModelTransFormObject {

    private String cmd;
    private String contextId;
    private List<Content> contents;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public ContextModelTransFormObject() {
    }

    public ContextModelTransFormObject(String cmd, String contextId, List<Content> contents, String time) {
        this.cmd = cmd;
        this.contextId = contextId;
        this.contents = contents;
        this.time = time;
    }
}

