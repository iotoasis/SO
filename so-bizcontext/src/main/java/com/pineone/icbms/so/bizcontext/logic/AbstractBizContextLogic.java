package com.pineone.icbms.so.bizcontext.logic;

import com.pineone.icbms.so.domain.entity.Domain;

/**
 * Created by melvin on 2016. 8. 3..
 */
abstract public class AbstractBizContextLogic implements BizContextLogic {

    @Override
    public boolean isHappenBizContext(Domain domain){
        return true;
    };

    @Override
    public int retrieveNeedQuantity(Domain domain){
        return 0;
    }
}
