package com.pineone.icbms.so.resources.property.reference;

import com.pineone.icbms.so.resources.vo.AGenericVirtualObject;

/**
 * Generic ontology reference abstract class.<BR/>
 * Created by uni4love on 2015. 10. 20..
 */
abstract public class AGenericOntologyReference extends AGenericVirtualObject
		implements IGenericOntologyReference
{
	/**
	 * reference
	 */
	protected String reference = null;

	@Override
	public String getReference()
	{
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
}
