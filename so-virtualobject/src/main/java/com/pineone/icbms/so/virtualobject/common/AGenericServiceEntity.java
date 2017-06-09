package com.pineone.icbms.so.virtualobject.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pineone.icbms.so.virtualobject.state.DefaultStateStore;
import com.pineone.icbms.so.virtualobject.state.IGenericStateStore;

/**
 * Orchestration Service abstract class.<BR/>
 *
 * Created by uni4love on 2016. 11. 18..
 */
abstract public class AGenericServiceEntity extends AGenericIdentity
        implements IGenericServiceEntity {
    /**
     * stateStore
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected DefaultStateStore<String, Object> stateStore;

    /**
     * return state.<BR/>
     *
     * @return state store
     */
    @Override
    public IGenericStateStore<String, Object> getStateStore() {
        return stateStore;
    }

    /**
     * set state store.<BR/>
     *
     * @param stateStore state store
     */
    public void setStateStore(DefaultStateStore<String, Object> stateStore) {
        this.stateStore = stateStore;
    }

    @Override
    public Object addState(String key, Object value) {
        if (stateStore == null)
            stateStore = new DefaultStateStore<>();
        return stateStore.addValue(key, value);
    }

    @Override
    public Object removeState(String key) {
        return stateStore.removeValue(key);
    }

    @Override
    public Object getState(String key) {
        return stateStore.getValue(key);
    }

    public Object updateState(String key, String value) {
        stateStore.removeValue(key);
        return stateStore.addValue(key, value);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        if (stateStore != null)
            sb.append(", stateStore: ").append(stateStore);
        return sb.toString();
    }
}
