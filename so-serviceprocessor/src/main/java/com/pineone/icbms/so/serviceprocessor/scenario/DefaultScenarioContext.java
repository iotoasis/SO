package com.pineone.icbms.so.serviceprocessor.scenario;

import com.pineone.icbms.so.serviceutil.interfaces.scenario.IScenarioContext;
import com.pineone.icbms.so.util.registry.AGenericKeyValueRegistry;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * scenario context default class.<BR/>
 *
 * Created by uni4love on 2017. 1. 12..
 */
public class DefaultScenarioContext extends AGenericKeyValueRegistry<String, String> implements IScenarioContext {
    /**
     * return interface for returning Store<BR/>
     *
     * @return Map
     */
    @Override
    protected Map<String, String> createStore() {
        return new LinkedHashMap<String, String>();
    }

    /**
     * return scenario id
     *
     * @return scenario id
     */
    @Override
    public String getScenarioId() {
        return this.getValue("SCENARIO_ID");
    }

    public void setScenearioId(String id) {
        this.addValue("SCENARIO_ID", id);
    }
}
