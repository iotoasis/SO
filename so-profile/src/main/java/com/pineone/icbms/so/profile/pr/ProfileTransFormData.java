package com.pineone.icbms.so.profile.pr;

/**
 * Created by melvin on 2016. 8. 23..
 * NOTE: 외부에 표현될 Profile 데이터
 */
public class ProfileTransFormData {

    private String id;
    private int period;

    public ProfileTransFormData() {
    }

    public ProfileTransFormData(String id) {
        this.id = id;
    }

    public ProfileTransFormData(String id, int period) {
        this.id = id;
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProfileTransFormData{" +
                "id='" + id + '\'' +
                ", period=" + period +
                '}';
    }
}
