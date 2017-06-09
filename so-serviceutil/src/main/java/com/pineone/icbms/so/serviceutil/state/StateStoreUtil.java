package com.pineone.icbms.so.serviceutil.state;

import com.pineone.icbms.so.virtualobject.common.AGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.common.IGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.state.DefaultStateStore;
import com.pineone.icbms.so.virtualobject.state.IGenericStateStore;

import java.util.Iterator;

/**
 * Created by uni4love on 2016. 5. 19..
 */
public class StateStoreUtil {

    /**
     *
     * @param stateStore
     * @return
     */
    public IGenericStateStore copyStateStore(IGenericStateStore<String, Object> stateStore) {
        DefaultStateStore<String, Object> store = null;
        if (stateStore != null) {
            store = new DefaultStateStore<>();
            Iterator<String> keys = stateStore.keySet().iterator();
            String key = null;
            while (keys.hasNext()) {
                key = keys.next();
                store.addValue(key, stateStore.getValue(key));
            }
        }
        return store;
    }

    /**
     * copy StateStore to ServiceEntity.<BR/>
     *
     * @param stateStore state store
     * @param serviceEntity service entity
     * @return IGenericServiceEntity
     */
    public static IGenericServiceEntity copyStateStore(IGenericStateStore stateStore, IGenericServiceEntity serviceEntity) {
        ((AGenericServiceEntity) serviceEntity).setStateStore((DefaultStateStore) stateStore);
        return serviceEntity;
    }
}
