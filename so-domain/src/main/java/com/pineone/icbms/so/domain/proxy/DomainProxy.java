package com.pineone.icbms.so.domain.proxy;

import com.pineone.icbms.so.domain.entity.Domain;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 2..
 */
public interface DomainProxy {
    List<Domain> retrieveDomainFromSDA();
}
