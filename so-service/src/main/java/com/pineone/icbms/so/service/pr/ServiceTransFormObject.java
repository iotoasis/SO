package com.pineone.icbms.so.service.pr;


import java.util.List;

public class ServiceTransFormObject {

    public ServiceTransFormObject(){};

    public ServiceTransFormObject(String name){
        this.name = name;
    }

    /**
     * Service 식별자
     * format : si-(장소명)-(vo명)-(status)
     * ex : 'si-classroom-blind-off'
     */
    private String id;

    /**
     * Service 이름
     * format : free
     * ex : 'turnoff-blind-service'
     */
    private String name;

    /**
     * Service 가 포함하고 있는 virtualObject 들의 식별자 리스트
     * format : vo-(디바이스명)-(제어명)
     * ex : 'vo-blind01-001-open-control'
     */
    private List<String> virtualObjectIdList;

    /**
     * Vo 가 가지고 있는 기능서비스
     * format : SDA 에서 제공하는 형태
     * ex : 'power-control'
     */
    private String virtualObjectService;

    /**
     * Vo 의 기능서비스를 실행시키기 위한 명령 상태
     * format : 실제 디바이스 명령 형태
     * ex : 'OFF'
     */
    private String status;

    /**
     * 서비스 생성 시간
     * format : yyyymmddhhmm
     * ex : '201608250930'
     */
    private long createTime;

    /**
     * 서비스 변경 시간
     * format : yyyymmddhhmm
     * ex : '201608250930'
     */
    private long modifiedTime;

    /**
     * 서비스 실행 필터 주기
     * format : milisecound
     * ex : 5000
     */
    private long filterTime;

    /**
     * 세션의 아이디
     * format : String
     * ex :
     */
    private String sessionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getVirtualObjectIdList() {
        return virtualObjectIdList;
    }

    public void setVirtualObjectIdList(List<String> virtualObjectIdList) {
        this.virtualObjectIdList = virtualObjectIdList;
    }

    public String getVirtualObjectService() {
        return virtualObjectService;
    }

    public void setVirtualObjectService(String virtualObjectService) {
        this.virtualObjectService = virtualObjectService;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ServiceTransFormObject(String id, String name, List<String> VirtualObjectIdList, String VirtualObjectService, String status) {

        this.id = id;
        this.name = name;
        this.virtualObjectIdList = VirtualObjectIdList;
        this.virtualObjectService = VirtualObjectService;
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public long getFilterTime() {
        return filterTime;
    }

    public void setFilterTime(long filterTime) {
        this.filterTime = filterTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public ServiceTransFormObject(String id, String name, List<String> virtualObjectIdList, String virtualObjectService, String status, long createTime, long modifiedTime, long filterTime, String sessionId) {
        this.id = id;
        this.name = name;
        this.virtualObjectIdList = virtualObjectIdList;
        this.virtualObjectService = virtualObjectService;
        this.status = status;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.filterTime = filterTime;
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "ServiceTransFormObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", virtualObjectIdList=" + virtualObjectIdList +
                ", virtualObjectService='" + virtualObjectService + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", modifiedTime=" + modifiedTime +
                ", filterTime=" + filterTime +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }

}
