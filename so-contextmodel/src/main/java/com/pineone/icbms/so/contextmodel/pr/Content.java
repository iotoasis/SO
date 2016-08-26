package com.pineone.icbms.so.contextmodel.pr;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 25..
 */
public class Content {
    String loc;
    String uri;
    String type;
    String deg;

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
