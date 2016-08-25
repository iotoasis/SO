package com.pineone.icbms.so.contextmodel.pr;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 22..
 * NOTE: 외부에 노출될 ContextModel Data - Presentation, Controller 에서 사용
 */
public class ContextModelTransFormObject {

    private String cmd;
    private String contextid;
    private List<String> domains;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getContextid() {
        return contextid;
    }

    public void setContextid(String contextid) {
        this.contextid = contextid;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }


}
