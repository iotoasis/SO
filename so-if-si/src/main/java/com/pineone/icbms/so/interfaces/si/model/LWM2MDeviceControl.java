package com.pineone.icbms.so.interfaces.si.model;

/**
 * Created by melvin on 2017. 4. 4..
 */

//SO - SI LWM2M Device Interface , con 값으로 base64 인코딩후 추가
public class LWM2MDeviceControl {

    /**
     * 값 : execute
     * 설명 : 고정
     */
    private String operation;

    /**
     * 값 : /1024/12/1, /1024/12/3 중 제어 희망하는 항목 택1
     * 설명 : LED : /1024/12/1, SOUND : /1024/12/3

     */
    private String resourceUri;

    /**
     * 값 : led, sound 중 제어 희망하는 항목 택1
     * 설명 :
     */
    private String displayName;

    /**
     * 값 : 000001
     * 설명 : 디바이스 제조사 넘버
     */
    private String oui;

    /**
     * 값 : LWM2M Client(raspberry)
     * 설명 : 디바이스 모델 이름
     */
    private String modelName;

    /**
     * 값 : 90:9F:33:EF:D8:ED
     * 설명 : 디바이스 시리얼 넘버
     */
    private String sn;

    /**
     * 값 : testlwm2mclient
     * 설명 : 고정
     */
    private String authId;

    /**
     * 값 : 1234567890abcdef1234567890abcdef
     * 설명 : 고정
     */
    private String authPwd;

    /**
     * 값 : 1
     * 설명 : 제어 값
     */
    private String sv;

    public LWM2MDeviceControl() {
    }

    public LWM2MDeviceControl(String operation, String resourceUri, String displayName, String oui, String modelName, String sn, String authId, String authPwd, String sv) {
        this.operation = operation;
        this.resourceUri = resourceUri;
        this.displayName = displayName;
        this.oui = oui;
        this.modelName = modelName;
        this.sn = sn;
        this.authId = authId;
        this.authPwd = authPwd;
        this.sv = sv;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getOui() {
        return oui;
    }

    public void setOui(String oui) {
        this.oui = oui;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getAuthld() {
        return authId;
    }

    public void setAuthld(String authld) {
        this.authId = authld;
    }

    public String getAuthPwd() {
        return authPwd;
    }

    public void setAuthPwd(String authPwd) {
        this.authPwd = authPwd;
    }

    public String getSv() {
        return sv;
    }

    public void setSv(String sv) {
        this.sv = sv;
    }

    @Override
    public String toString() {
        return "LWM2MDeviceControl{" +
                "operation='" + operation + '\'' +
                ", resourceUri='" + resourceUri + '\'' +
                ", displayName='" + displayName + '\'' +
                ", oui='" + oui + '\'' +
                ", modelName='" + modelName + '\'' +
                ", sn='" + sn + '\'' +
                ", authId='" + authId + '\'' +
                ", authPwd='" + authPwd + '\'' +
                ", sv='" + sv + '\'' +
                '}';
    }
}
