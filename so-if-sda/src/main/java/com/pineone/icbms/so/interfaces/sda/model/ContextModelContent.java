package com.pineone.icbms.so.interfaces.sda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Context contents model.<BR/>
 *
 * Created by uni4love on 2017. 4. 4..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContextModelContent {
    /**
     * location list
     */
    @Getter@Setter
    @JsonProperty("loc")
    String locationUri;

    /**
     * functionList
     */
    @Getter@Setter
    @JsonProperty("function")
    String functionUri;

    /**
     * aspect List
     */
    @Getter@Setter
    @JsonProperty("aspect")
    String aspectUri;

    /**
     * Device List
     */
    @Getter@Setter
    @JsonProperty("device")
    String deviceUri;

    /**
     * ci_sequence
     */
    @Getter@Setter
    @JsonProperty("ci_seq")
    String ci_sequence_number;

    /**
     * ci_id
     */
    @Getter@Setter
    @JsonProperty("ciid")
    String ci_id;

    /**
     * ci_name
     */
    @Getter@Setter
    @JsonProperty("ciname")
    String ci_name;

    /**
     * Last value of Device
     */
    @Getter@Setter
    @JsonProperty("latest_val")
    String value;

    /*
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDeviceUri() {
        return deviceUri;
    }

    public void setDeviceUri(String deviceUri) {
        this.deviceUri = deviceUri;
    }

    public String getFunctionUri() {
        return functionUri;
    }

    public String getLocationUri() {
        return locationUri;
    }

    public void setFunctionUri(String functionUri) {
        this.functionUri = functionUri;
    }

    public String getAspectUri() {
        return aspectUri;
    }

    public void setAspectUri(String aspectUri) {
        this.aspectUri = aspectUri;
    }

    public void setLocationUri(String locationUri) {
        this.locationUri = locationUri;
    }

    public String getCi_sequence_number() {
        return ci_sequence_number;
    }

    public void setCi_sequence_number(String ci_sequence_number) {
        this.ci_sequence_number = ci_sequence_number;
    }

    public String getCi_id() {
        return ci_id;
    }

    public void setCi_id(String ci_id) {
        this.ci_id = ci_id;
    }

    public String getCi_name() {
        return ci_name;
    }

    public void setCi_name(String ci_name) {
        this.ci_name = ci_name;
    }
*/
//    @Override
//    public String toString() {
//        StringBuffer sb = new StringBuffer(getClass().getSimpleName() + "{");
//        if(locationUri != null)
//            sb.append("locationUri: ").append(locationUri);
//        sb.append("}");
//        if(functionUri != null)
//            sb.append("functionUri: ").append(functionUri);
//        sb.append("}");
//        if(aspectUri != null)
//            sb.append("aspectUri: ").append(aspectUri);
//        sb.append("}");
//        if(deviceUri != null)
//            sb.append("deviceUri: ").append(deviceUri);
//        sb.append("}");
//        if(ci_sequence_number != null)
//            sb.append("ci_number: ").append(ci_sequence_number);
//        sb.append("}");
//        if(ci_id != null)
//            sb.append("ci_id: ").append(ci_id);
//        sb.append("}");
//        if(ci_name != null)
//            sb.append("ci_name: ").append(ci_name);
//        sb.append("}");
//        if(ci_name != null)
//            sb.append("value: ").append(value);
//        sb.append("}");
//        return sb.toString();
//    }

/*
    @Override
    public String toString() {
        return "ContextModelContent{" +
                "locationUri='" + locationUri + '\'' +
                ", functionUri='" + functionUri + '\'' +
                ", aspectUri='" + aspectUri + '\'' +
                ", deviceUri='" + deviceUri + '\'' +
                ", ci_sequence_number='" + ci_sequence_number + '\'' +
                ", ci_id='" + ci_id + '\'' +
                ", ci_name='" + ci_name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
*/
}
