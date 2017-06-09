package com.pineone.icbms.so.interfaces.sda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pineone.icbms.so.virtualobject.common.AGenericIdNameOwner;

/**
 * ContextInformationForIf model class for SDA interface.<BR/>
 *
 * Created by uni4love on 2017. 1. 19..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContextInformationForIf extends AGenericIdNameOwner {


    /**
     * uri
     */
    String uri;


    /**
     * ci_sequence_number
     */
    String number;



    /**
     * contructor
     */
    public ContextInformationForIf() {
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public ContextInformationForIf(String id, String name) {
        super(id, name);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

//    @Override
//    public String toString() {
//        StringBuffer sb = new StringBuffer();
//        sb.append("[id = ").append(id);
//        sb.append(", name = ").append(name);
//        sb.append(", uri: ").append(uri);
//        sb.append(", number: ").append(number);
//        sb.append("]");
//        return sb.toString();
//    }


    @Override
    public String toString() {
        return "ContextInformationForIf{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
