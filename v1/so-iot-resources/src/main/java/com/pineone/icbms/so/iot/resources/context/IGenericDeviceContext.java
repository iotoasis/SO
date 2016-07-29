package com.pineone.icbms.so.iot.resources.context;

import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

import java.util.List;

/**
 * Generic device context.<BR/>
 * Created by uni4love on 2015. 10. 15..
 */
public interface IGenericDeviceContext<V>
		extends IGenericContext<String, V>
{
	/**
	 * return ontology reference.<BR/>
	 * 
	 * @return ontology reference
	 */
	IGenericOntologyReference getOntologyReference();

	/**
	 * return model deviceModelList.<BR/>
	 * 
	 * @return model deviceModelList
	 */
	List<String> getDeviceModelList();

	/**
	 * return device id.<BR/>
	 * 
	 * @return device id
	 */
	String getDeviceID();

	/**
	 * return device control value.<BR/>
	 * 
	 * @return device control value
	 */
	String getDeviceControlValue();

}
