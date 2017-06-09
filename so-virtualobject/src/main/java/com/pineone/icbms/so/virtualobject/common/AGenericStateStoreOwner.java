package com.pineone.icbms.so.virtualobject.common;

import com.pineone.icbms.so.virtualobject.state.IGenericStateStore;

/**
 * abstract Generic state owner.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
abstract public class AGenericStateStoreOwner
        implements IGenericStateStoreOwner<String, Object, IGenericStateStore<String, Object>> {

    /**
     * state
     */
    protected IGenericStateStore stateStore;

    /**
     * constuctor
     */
    public AGenericStateStoreOwner() {
    }

    @Override
    public IGenericStateStore getStateStore() {
        return stateStore;
    }

    public void setStateStore(IGenericStateStore state) {
        this.stateStore = state;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(", stateStore: ").append(stateStore);
        return sb.toString();
    }
}
