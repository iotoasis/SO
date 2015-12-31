package com.pineone.icbms.so.resources.context;

import com.pineone.icbms.so.resources.registry.IKeyValueRegistry;

/**
 * generic context interface.<BR/>
 * Created by uni4love on 2015. 05. 30..
 */
public interface IGenericContext<String, V> extends IContext<Long, String>, IKeyValueRegistry<String, V>
{
}
