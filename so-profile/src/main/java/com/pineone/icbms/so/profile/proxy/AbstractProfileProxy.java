package com.pineone.icbms.so.profile.proxy;

import com.pineone.icbms.so.contextmodel.entity.ContextModel;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 19..
 */
public abstract class AbstractProfileProxy implements ProfileProxy{
    //
    @Override
    public List<String> retrieveContextModelNameList() {
        return null;
    }

    @Override
    public List<String> retrieveServiceModelNameList() {
        return null;
    }

    @Override
    public List<String> retrieveBizContextList() {
        return null;
    }

    @Override
    public boolean checkContextModelQueue() {
        return false;
    }

    @Override
    public ContextModel retrieveContextModelQueueData() {
        return null;
    }
}
