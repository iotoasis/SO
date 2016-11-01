package com.pineone.icbms.so.profile.proxy;

import com.pineone.icbms.so.contextmodel.entity.ContextModel;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 11..
 */
public interface ProfileProxy {
    List<String> retrieveContextModelNameList();

    List<String> retrieveServiceModelNameList();

    List<String> retrieveBizContextList();

    boolean checkContextModelQueue();

    ContextModel retrieveContextModelQueueData();

    void registerScheduler(String profileId, int period);
}
