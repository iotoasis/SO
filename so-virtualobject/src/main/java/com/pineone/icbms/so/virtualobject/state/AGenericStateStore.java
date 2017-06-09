package com.pineone.icbms.so.virtualobject.state;

import com.pineone.icbms.so.util.registry.AGenericKeyValueRegistry;

/**
 * State abstract class.<BR/>
 *
 * Created by uni4love on 2016. 12. 8..
 */
abstract public class AGenericStateStore<K, V> extends AGenericKeyValueRegistry<K, V>
        implements IGenericStateStore<K, V> {
}
