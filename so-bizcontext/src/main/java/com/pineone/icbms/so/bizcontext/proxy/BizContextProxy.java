package com.pineone.icbms.so.bizcontext.proxy;

import com.pineone.icbms.so.domain.entity.Domain;

/**
 * Created by melvin on 2016. 8. 4..
 */
public interface BizContextProxy {
    //
    int retrieveCurrentValue(Domain domain);
    int retrievePastValue(Domain domain);
    int retrieveAllDeviceUseAmount(Domain domain);
    int retrieveObjectValue(Domain domain);
    int retrieveCurrentClassPCAmount(Domain domain);
    int retrieveNextClassPCAmount(Domain domain);
    int retrieveCurrentClassMouseAmount(Domain domain);
    int retrieveNextClassMouseAmount(Domain domain);
    int retrieveCurrentClassKeyBoardAmount(Domain domain);
    int retrieveNextClassKeyBoardAmount(Domain domain);
}
