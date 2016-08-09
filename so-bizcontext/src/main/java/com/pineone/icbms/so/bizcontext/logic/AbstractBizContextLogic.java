package com.pineone.icbms.so.bizcontext.logic;

/**
 * Created by melvin on 2016. 8. 3..
 */
abstract public class AbstractBizContextLogic implements BizContextLogic {

    @Override
    public boolean isHappenBizContext(){
        return true;
    };

    @Override
    public int retrieveNeedQuantity(){
        return 0;
    }
}
