package com.pineone.icbms.so.resources.context;

import com.pineone.icbms.so.resources.registry.IKeyValueRegistry;

/**
 * Generic context interface.<BR/>
 * Created by uni4love on 2015. 05. 30..
 */
public interface IGenericContext<K extends String, V>
		extends IContext, IKeyValueRegistry<String, V>
{
}
