package com.pineone.icbms.so.virtualobject.common;

import com.pineone.icbms.so.virtualobject.state.IStateStore;

/**
 * State interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
public interface IStateStoreOwner<K, V, STORE extends IStateStore<K, V>> {
    /**
     * return state.<BR/>
     *
     * @return state store
     */
    STORE getStateStore();

//    /**
//     * set state store.<BR/>
//     *
//     * @param stateStore state store
//     */
//    void setStateStore(STORE stateStore);

    /**
     * add a state.<BR/>
     *
     * @param key key
     * @param value value
     * @return added value
     */
    V addState(K key, V value);

    /**
     * get a state by key.<BR/>
     *
     * @param key key
     * @return value
     */
    V getState(K key);

    /**
     * remove a state.<BR/>
     *
     * @param key key
     * @return remvoed value
     */
    V removeState(K key);
}
