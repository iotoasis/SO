package com.pineone.icbms.so.iot.resources.context;

import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.property.IGenericOntologyReference;
import com.pineone.icbms.so.resources.property.IValue;

import java.util.List;

/**
 * Generic device context.<BR/>
 * Created by uni4love on 2015. 10. 15..
 */
public interface IGenericDeviceContext<V> extends IDeviceContext<Long, String>, IGenericContext<String, V>
{
	/**
	 * return ontology reference.<BR/>
	 * @return ontology reference
     */
	IGenericOntologyReference getOntologyReference();

	/**
	 * return model list.<BR/>
	 * @return model list
     */
	List<String> getModelList();

	/**
	 * return device id.<BR/>
	 * @return device id
	 */
	String getDeviceID(String value);

	/**
	 * return device controll value.<BR/>
	 * @return device controll value
	 */
	IValue getDeviceControllValue(String value);

}
