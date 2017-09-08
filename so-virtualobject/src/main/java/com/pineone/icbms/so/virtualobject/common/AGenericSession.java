package com.pineone.icbms.so.virtualobject.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * abstract Generic session owner.<BR/>
 *
 * Created by jonghee on 2016. 11. 16..
 */
@ToString
abstract public class AGenericSession implements IGenericSession<String> {
    /**
     * sessionId
     */
    @Getter @Setter
    private String sessionId;

    /**
     * constuctor
     */
    public AGenericSession() {
    }

    /**
     * constructor<BR/>
     *
     * @param sessionId   sessionId
     */
    public AGenericSession(String sessionId) {
        this.sessionId = sessionId;
    }
}
