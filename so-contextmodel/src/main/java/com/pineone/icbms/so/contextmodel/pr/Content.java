package com.pineone.icbms.so.contextmodel.pr;

/**
 * Created by melvin on 2016. 8. 25..
 */
//NOTE: SDA 에서 수신받는 데이터 중 Content 내부의 Key 값 형성
public class Content {
    String loc;
    String uri;
    String type;
    String deg;
    String place;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public String getLoc() {
        return loc;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
