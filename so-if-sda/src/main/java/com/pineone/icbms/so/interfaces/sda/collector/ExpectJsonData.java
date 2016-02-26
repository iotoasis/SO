package com.pineone.icbms.so.interfaces.sda.collector;

/**
 * SDA와 연동에서 content 내용 안의 리스트로 받을 수 있는 모든 정보를 정의
 * Created by Melvin on 2016. 1. 7..
 */
public class ExpectJsonData {

    /**
     * Uri
     */
    String uri;

    /**
     * Location Information
     */
    String loc;

    /**
     * Person Information
     */
    String person;

    /**
     * Device Information
     */
    String dev;

    /**
     * Minimum Value
     */
    double min_value;

    /**
     * Maximum Value
     */
    double max_value;

    /**
     * Manager Information(Specific Location)
     */
    String manager;

    /**
     * User id(Detail Information)
     */
    String user_id;

    /**
     * User's Phone(Detail Information)
     */
    String phone;

    /**
     * User's name(Detail Information)
     */
    String name;

    /**
     * User's Student_id(Detail Information)
     */
    String student_id;

    /**
     * Device OperationType
     */
    String type;

    /**
     * URL only cam used
     */
    String camurl;

    /**
     * Response Message
     */
    String msg;

    /**
     * Condition Information
     */

    String cond;

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

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

    public String getCamurl() {
        return camurl;
    }

    public void setCamurl(String camurl) {
        this.camurl = camurl;
    }
}


