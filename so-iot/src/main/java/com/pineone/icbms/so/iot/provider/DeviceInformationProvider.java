package com.pineone.icbms.so.iot.provider;

import java.util.List;
import com.pineone.icbms.so.interfaces.repository.DeviceInformationRepoInterface;

public class DeviceInformationProvider extends AGeneralProvider {

	private static DeviceInformationRepoInterface deviceInformationRepoInterface;

	/**
	 * 
	 * @param cl ModelObject Class
	 */
	public DeviceInformationProvider(){
		if(deviceInformationRepoInterface == null){
			deviceInformationRepoInterface = new DeviceInformationRepoInterface();
		}
	}
	
	/**
	 * get DeviceInformation Data by id (resource from MongoDB)
	 * @param id DeviceInformation Document id Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByID(String id, Class<T> cls){
		return deviceInformationRepoInterface.getData(id_str, id, cls); 
	}
	
	/**
	 * get DeviceInformation Data by name (resource from MongoDB)
	 * @param name DeviceInformation Document name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByName(String name, Class<T> cls){
		return deviceInformationRepoInterface.getData(name_str, name, cls);
	}
	
	/**
	 * get DeviceInformation Data List by name (resource from MongoDB)
	 * @param name DeviceInformation Documents name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> List<T> getDataListByName(String name, Class<T> cls){
		return deviceInformationRepoInterface.getDataList(name_str, name, cls);  
	}
	
	/**
	 * get DeviceInformation Data by 'physical device model name' (resource from MongoDB)
	 * @param model DeviceInformation Object model
	 * @param cls return modelObjectClass
	 * @return DeviceInformation Object
	 */
	public <T> T getDataByModel(String model, Class<T> cls){
		return deviceInformationRepoInterface.getData("model", model, cls);
	}
	
	/**
	 *  get DeviceInformation Data by 'device Serial Number' (resource from MongoDB)
	 * @param sn Serial Number
	 * @param cls return modelObjectClass
	 * @return DeviceInformation Object
	 */
	public <T> T getDataBySN(String sn, Class<T> cls){
		return deviceInformationRepoInterface.getData("sn", sn, cls);
	}
	
	/**
	 *  get DeviceInformation Data by 'device driver's ID' (resource from MongoDB)
	 * @param deviceDriverID DeviceDriverModel's ID
	 * @param cls return modelObjectClass
	 * @return DeviceInformation Object
	 */
	public <T> T getDataByDeviceDriverID(String deviceDriverID, Class<T> cls){
		return deviceInformationRepoInterface.getData("deviceDriverID", deviceDriverID, cls);
	}
	
}
