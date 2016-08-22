package com.pineone.icbms.so.contextmodel.proxy;

import com.pineone.icbms.so.domain.entity.Domain;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 19..
 */
public interface ContextModelInProxy {
    //
    List<String> retrieveContextInformationNameList();
    List<Domain> retrieveDomainList();
}
