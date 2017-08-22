package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by jonghee on 2017-06-02.
 */
//@Data
@JsonPropertyOrder({"sessionId","seq", "description"})
@ToString
public class TrackingEntity implements Serializable {
    /*
     * just increment id
     */
    //private String id;

    @Getter @Setter
    private String sessionId;

    @Getter @Setter
    private int seq;

    /*
     * simulator_type
     *      web: web simulation
     *      log: only logging
     *      null: real service
     */
    @Getter @Setter
    private String simulatorType;

    @Getter @Setter
    private String method;

    @Getter @Setter
    private String uri;

    @Getter @Setter
    private String remoteAddr;

    @Getter @Setter
    private String remoteHost;

    @Getter @Setter
    private String createdTime;

    @Getter @Setter
    private String process;

    @Getter @Setter
    private String processId;

    @Getter @Setter
    private String processName;

    @Getter @Setter
    private String processValue;

    @Getter @Setter
    private String processMethod;

    @Getter @Setter
    private String processResult;

    @Getter @Setter
    private String userId;

    /*
     * 디바이스 실행을 위해 si 컨테이너에 넘기는 commandId
     */
    @Getter @Setter
    private String commandId;

    @Getter @Setter
    private String statusCd;

    public void clearRequestInfomation() {
        this.method = "";
        this.remoteAddr = "";
        this.remoteHost = "";
        //this.simulatorType = "";
        this.uri = "";
    }

    public void clearProcessInfomation() {
        this.process = "";
        this.processMethod = "";
        this.processName = "";
        this.processId = "";
        this.processResult = "";
        this.processValue = "";
        //this.simulatorType = "";
        //this.uri = "";
    }

}
