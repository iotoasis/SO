package com.pineone.icbms.so.bizcontext.proxy;

/**
 * Created by melvin on 2016. 8. 4..
 */
public interface BizContextProxy {
    //
    int retrieveCurrentValue();
    int retrievePastValue();
    int retrieveAllDeviceUseAmount();
    int retrieveObjectValue();
    int retrieveCurrentClassPCAmount();
    int retrieveNextClassPCAmount();
    int retrieveCurrentClassMouseAmount();
    int retrieveNextClassMouseAmount();
    int retrieveCurrentClassKeyBoardAmount();
    int retrieveNextClassKeyBoardAmount();
}
