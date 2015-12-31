package com.pineone.icbms.so.resources.vo;

import com.pineone.icbms.so.resources.property.IIdOwner;
import com.pineone.icbms.so.resources.property.INameOwner;

/**
 * Virtual Object interface.<BR/>
 * Created by uni4love on 2015. 5. 7..
 */
public interface IVirtualObject<ID, NAME> extends IIdOwner<ID>, INameOwner<NAME>
{
	/**
	 * return virtual object id<BR>
	 *
	 * @return ID
	 */
	ID getId();

	/**
	 * return virtual object name<BR>
	 *
	 * @return NAME
	 */
	NAME getName();
}
