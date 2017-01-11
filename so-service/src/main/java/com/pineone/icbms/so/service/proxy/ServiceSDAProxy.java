package com.pineone.icbms.so.service.proxy;

import com.pineone.icbms.so.util.exception.BadRequestException;
import com.pineone.icbms.so.util.session.Session;

public interface ServiceSDAProxy {
    String getPCCountUri(Session session) throws BadRequestException;
    String getTemperatureLookup(Session session) throws BadRequestException;
}
