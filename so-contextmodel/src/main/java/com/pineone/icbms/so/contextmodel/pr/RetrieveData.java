package com.pineone.icbms.so.contextmodel.pr;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 25..
 */
//NOTE: SDA 에서 수신받는 데이터 형태 반영
public class RetrieveData {

    String cmd;
    List<Content> contents;
    String time;
    String contextId;

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public List<Content> getContent() {
        return contents;
    }

    public void setContent(List<Content> content) {
        this.contents = content;
    }
}
