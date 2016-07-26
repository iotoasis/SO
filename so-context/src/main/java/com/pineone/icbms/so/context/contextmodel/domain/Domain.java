package com.pineone.icbms.so.context.contextmodel.domain;

import java.util.List;

/**
 * Created by melvin on 2016. 7. 25..
 */
public interface Domain {

    String getName();
    String getUri();
    void setName(String name);
    void setUri(String uri);
    List<Domain> retrieveDomainList();
}
