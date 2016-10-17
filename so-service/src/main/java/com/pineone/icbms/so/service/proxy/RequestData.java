package com.pineone.icbms.so.service.proxy;

/**
 * Created by melvin on 2016. 10. 13..
 */
public class RequestData {
    private String deviceUri;
    private String count;
    private String forecastelectric;

    public RequestData() {
    }

    public RequestData(String deviceUri, String count, String forecastelectric) {
        this.deviceUri = deviceUri;
        this.count = count;
        this.forecastelectric = forecastelectric;
    }

    public String getDeviceUri() {
        return deviceUri;
    }

    public void setDeviceUri(String deviceUri) {
        this.deviceUri = deviceUri;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getForecastelectric() {
        return forecastelectric;
    }

    public void setForecastelectric(String forecastelectric) {
        this.forecastelectric = forecastelectric;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "deviceUri='" + deviceUri + '\'' +
                ", count='" + count + '\'' +
                ", forecastelectric='" + forecastelectric + '\'' +
                '}';
    }
}
