package com.pineone.icbms.so.bizcontext.logic;

import com.pineone.icbms.so.domain.entity.Domain;

/**
 * Created by melvin on 2016. 8. 3..
 */
public interface BizContextLogic {
    //
    boolean isHappenBizContext(Domain domain);
    int retrieveNeedQuantity(Domain domain);
}
