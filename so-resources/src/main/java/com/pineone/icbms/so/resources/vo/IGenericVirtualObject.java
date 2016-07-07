package com.pineone.icbms.so.resources.vo;

/**
 * Generic virtual object.<BR/>
 * Created by uni4love on 2015. 10. 7..
 */
public interface IGenericVirtualObject
		extends IVirtualObject<String, String>
{
	@Override
	String getId();

	@Override
	String getName();
}
