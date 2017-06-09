package com.pineone.icbms.so.virtualobject.state;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

/**
 * state default class.<BR/>
 *
 * Created by uni4love on 2017. 1. 5..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value= JsonInclude.Include.NON_ABSENT, content= JsonInclude.Include.NON_EMPTY)
public class DefaultStateStore<K, V> extends AGenericStateStore<K, V> {
    /**
     * return interface for returning Store<BR/>
     *
     * @return Map
     */
    @Override
    public Map<K, V> createStore() {
        return new HashMap<K, V>();
    }
}