package com.pineone.icbms.so.interfaces.messagequeue.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Location model for MQ.<BR/>
 *
 * Created by uni4love on 2017. 4. 5..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value= JsonInclude.Include.NON_ABSENT, content= JsonInclude.Include.NON_EMPTY)
public class LocationForMQ extends UriOwnerForMQ {
    public String toString() {
        StringBuffer sb = new StringBuffer("location: {");
        sb.append("uri: ").append(uri);
        sb.append("}");
        return sb.toString();
    }
}
