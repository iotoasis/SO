package com.pineone.icbms.so.contextmodel.proxy;

import com.pineone.icbms.so.contextmodel.entity.Domain;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 2..
 */
public interface DomainProxy {
    List<Domain> retrieveDomainFromSDA();
}
