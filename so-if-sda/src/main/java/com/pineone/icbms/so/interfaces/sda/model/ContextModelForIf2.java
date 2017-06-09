package com.pineone.icbms.so.interfaces.sda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pineone.icbms.so.virtualobject.common.AGenericIdNameOwner;

import java.util.List;

/**
 * ContextModelForIf model class for SDA interface.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 19..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
public class ContextModelForIf2 extends AGenericIdNameOwner {

    @JsonProperty("simulatorType")
    String simulatorType;

    /**
     * command
     */
    @JsonProperty("cmd")
    String cmd;

    /**
     * context id
     */
    @JsonProperty("contextId")
    String contextId;

    /**
     * time
     */
    @JsonProperty("time")
    String time;

    /**
     * context contents
     */
    @JsonProperty("contents")
    List<ContextModelContent> contextModelContentList;

    /**
     * contructor
     */
    public ContextModelForIf2() {
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

    public List<ContextModelContent> getContextModelContentList() {
        return contextModelContentList;
    }

    public void setContextModelContentList(List<ContextModelContent> contextModelContentList) {
        this.contextModelContentList = contextModelContentList;
    }

    public String getSimulatorType() {
        return simulatorType;
    }

    public void setSimulatorType(String simulatorType) {
        this.simulatorType = simulatorType;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName()).append(": {");
        sb.append("simulatorType: ").append(simulatorType)
            .append("cmd: ").append(cmd)
            .append(", contextId: ").append(contextId)
            .append(", time: ").append(time);
        if (contextModelContentList != null) {
            sb.append(", ");
            for (ContextModelContent contents : contextModelContentList) {
                sb.append(contents).append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
