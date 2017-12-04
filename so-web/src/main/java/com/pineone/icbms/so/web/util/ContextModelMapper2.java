package com.pineone.icbms.so.web.util;

import com.pineone.icbms.so.interfaces.messagequeue.model.ContextModelForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.model.LocationForMQ;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelContent;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelForIf;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelForIf2;
import com.pineone.icbms.so.util.conversion.JsonMapper;

import java.sql.Timestamp;

/**
 * Model mapper utility class.<BR/>
 *
 * Created by uni4love on 2016. 12. 26..
 */
public class ContextModelMapper2 extends JsonMapper {
    /**
     * convert ContextModelForIf to ContextModelForMQ.<BR/>
     *
     * @return ContextModelForMQ
     */
    public static ContextModelForMQ toContextModelForMQ(ContextModelForIf contextModelForIf) {
        ContextModelForMQ cmForMQ = null;
        if(contextModelForIf != null) {
            cmForMQ = new ContextModelForMQ(contextModelForIf.getId(), contextModelForIf.getName());

            //timestamp
            cmForMQ.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        }
        return cmForMQ;
    }

    /**
     * convert ContextModelForIf2 to ContextModelForMQ.<BR/>
     *
     * @return ContextModelForMQ
     */
    public static ContextModelForMQ toContextModelForMQ(ContextModelForIf2 retrieveContextData) {
        ContextModelForMQ cmForMQ = null;
        if(retrieveContextData != null) {
            cmForMQ = new ContextModelForMQ(retrieveContextData.getContextId(), retrieveContextData.getName());
            LocationForMQ locationForMQ = null;
            if (retrieveContextData.getContextModelContentList() != null) {
                for (ContextModelContent ci : retrieveContextData.getContextModelContentList()) {
                    locationForMQ = new LocationForMQ();
                    locationForMQ.setUri(ci.getLocationUri());
                    cmForMQ.addLocation(locationForMQ);
                }
            }
            //timestamp
            cmForMQ.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        }
        return cmForMQ;
    }
}
