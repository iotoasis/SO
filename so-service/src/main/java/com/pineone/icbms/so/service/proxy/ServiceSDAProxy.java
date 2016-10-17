package com.pineone.icbms.so.service.proxy;

import com.pineone.icbms.so.util.exception.BadRequestException;

/**
 * Created by melvin on 2016. 10. 13..
 */
public interface ServiceSDAProxy {
    String getPCCountUri() throws BadRequestException;
}
