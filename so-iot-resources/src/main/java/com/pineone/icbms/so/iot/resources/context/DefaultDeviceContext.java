package com.pineone.icbms.so.iot.resources.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * Device context default class.<BR/>
 * Created by uni4love on 2015. 11. 5..
 */
public class DefaultDeviceContext extends AGenericDeviceContext<Object>
{
	/**
	 * Device Model List
	 */
	protected ArrayList<String> deviceModelList = new ArrayList<>();

	/**
	 * Ontology Reference
	 */
	protected DefaultOntologyReference ontologyReference = new DefaultOntologyReference();

	/**
	 * Device ID
	 */
	protected String deviceId = null;

	/**
	 * Device Control Value
	 */
	protected String deviceControlValue = null;

	/**
	 * constructor
	 */
	public DefaultDeviceContext()
	{
	}

	/**
	 * constructor
	 * @param deviceModelList device model list
	 * @param ontologyReference ontology reference
	 * @param deviceId device id
	 * @param deviceControlValue device control value
     */
	public DefaultDeviceContext(ArrayList<String> deviceModelList,
			DefaultOntologyReference ontologyReference, String deviceId,
			String deviceControlValue)
	{
		this.deviceModelList = deviceModelList;
		this.ontologyReference = ontologyReference;
		this.deviceId = deviceId;
		this.deviceControlValue = deviceControlValue;
	}

	@Override
	protected Map<String, Object> createStore()
	{
		return new ConcurrentHashMap<String, Object>();
	}

	@Override
	public IGenericOntologyReference getOntologyReference()
	{
		return ontologyReference;
	}

	public void setOntologyReference(DefaultOntologyReference ontologyReference)
	{
		this.ontologyReference = ontologyReference;
	}

	@Override
	public List<String> getDeviceModelList()
	{
		return deviceModelList;
	}

	public void setDeviceModelList(ArrayList<String> deviceModelList)
	{
		this.deviceModelList = deviceModelList;
	}

	@Override
	public String getDeviceID()
	{
		return deviceId;
	}

	@Override
	public String getDeviceControlValue()
	{
		return deviceControlValue;
	}

	public void setDeviceControlValue(String deviceControlValue)
	{
		this.deviceControlValue = deviceControlValue;
	}

	public String getDeviceId()
	{
		return deviceId;
	}

	public void setDeviceId(String deviceId)
	{
		this.deviceId = deviceId;
	}
}
