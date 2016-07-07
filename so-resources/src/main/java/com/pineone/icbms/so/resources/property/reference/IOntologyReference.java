package com.pineone.icbms.so.resources.property.reference;

import com.pineone.icbms.so.resources.property.IIdOwner;
import com.pineone.icbms.so.resources.property.INameOwner;
import com.pineone.icbms.so.resources.property.IProperty;

/**
 * Ontology reference interface.<BR/>
 * Created by uni4love on 2015. 07. 10..
 */
public interface IOntologyReference<ID, NAME>
		extends IProperty, IIdOwner<ID>, INameOwner<NAME>
{
}
