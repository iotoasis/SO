package com.pineone.icbms.so.resources.property.operation;

import com.pineone.icbms.so.resources.property.IIdOwner;
import com.pineone.icbms.so.resources.property.INameOwner;
import com.pineone.icbms.so.resources.property.IProperty;

/**
 * Operation value interface.<BR/>
 * Created by uni4love on 2016. 1. 12..
 */
public interface IOperationValue<ID, NAME>
		extends IProperty, IIdOwner<ID>, INameOwner<NAME>
{
}
