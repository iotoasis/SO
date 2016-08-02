package com.pineone.icbms.so.context_model.proxy;

import com.pineone.icbms.so.context_model.entity.Domain;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 2..
 */
public interface DomainProxy {
    List<Domain> retrieveDomainFromSDA();
}
