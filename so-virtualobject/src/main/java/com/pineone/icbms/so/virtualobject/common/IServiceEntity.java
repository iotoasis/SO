package com.pineone.icbms.so.virtualobject.common;

import com.pineone.icbms.so.virtualobject.state.IGenericStateStore;

/**
 * Orchestration Service entity interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 18..
 */
public interface IServiceEntity<K, V, STORE extends IGenericStateStore<K, V>>
        extends IGenericIdentity, IGenericStateStoreOwner<K, V, IGenericStateStore<K, V>> {
}
