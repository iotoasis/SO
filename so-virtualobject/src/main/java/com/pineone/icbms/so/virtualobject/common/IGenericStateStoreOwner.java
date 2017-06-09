package com.pineone.icbms.so.virtualobject.common;

import com.pineone.icbms.so.virtualobject.state.IGenericStateStore;

/**
 * State Owner generic interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
public interface IGenericStateStoreOwner<K, V, STORE extends IGenericStateStore<K, V>>
        extends IStateStoreOwner<K, V, IGenericStateStore<K, V>> {
    /**
     * return state.<BR/>
     *
     * @return state store
     */
    @Override
    IGenericStateStore<K, V> getStateStore();

//    /**
//     * set state store.<BR/>
//     *
//     * @param stateStore state store
//     */
//    @Override
//    void setStateStore(IGenericStateStore<K, V> stateStore);
}
