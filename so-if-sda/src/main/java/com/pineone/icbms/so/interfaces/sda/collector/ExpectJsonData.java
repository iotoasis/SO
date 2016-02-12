package com.pineone.icbms.so.interfaces.sda.collector;

/**
 * SDA와 연동에서 content 내용 안의 리스트로 받을 수 있는 모든 정보를 정의
 * Created by Melvin on 2016. 1. 7..
 */
public class ExpectJsonData {

    /**
     * CAM 정보 및 다른 URI를 데이터로 받기위한 변수
     */
    String uri;

    /**
     * Location 정보를 데이터로 받기위한 변수
     */
    String loc;

    /**
     * 사람 정보를 데이터로 받기위한 변수
     */
    String person;

    /**
     * device 정보를 데이터로 받기위한 변수
     */
    String dev;

    /**
     * 최소값 정보를 데이터로 받기위한 변수
     */
    double min_value;

    /**
     * 최대값 정보를 데이터로 받기위한 변수
     */
    double max_value;

    /**
     * 관리인(특정 장소) 정보를 데이터로 받기위한 변수
     */
    String manager;

    /**
     * 유저 정보를 데이터로 받기위한 변수
     */
    String user_id;

    /**
     * 유저 정보를 데이터로 받기위한 변수
     */
    String phone;

    /**
     * 유저 정보를 데이터로 받기위한 변수
     */
    String name;

    /**
     * 유저 정보를 데이터로 받기위한 변수
     */
    String student_id;

    /**
     * Device OperationType을 수신받기 위한 변수
     */
    String type;

    /**
     * 200응답코드가 아닌 다른 응답에러일경우 메세지 저장을 위한 벼누
     */
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public double getMax_value() {
        return max_value;
    }

    public void setMax_value(double max_value) {
        this.max_value = max_value;
    }

    public double getMin_value() {

        return min_value;
    }

    public void setMin_value(double min_value) {
        this.min_value = min_value;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getLoc() {

        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
