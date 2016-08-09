package com.pineone.icbms.so.iot.provider;

import java.util.List;
import com.pineone.icbms.so.interfaces.repository.DeviceDriverRepoInterface;

public class DeviceDriverProvider extends AGeneralProvider {

	private static DeviceDriverRepoInterface deviceDriverRepoInterface;

	/**
	 * 
	 * @param cl ModelObject Class
	 */
	public DeviceDriverProvider(){
		if(deviceDriverRepoInterface == null){
			deviceDriverRepoInterface = new DeviceDriverRepoInterface();
		}
	}
	
	/**
	 * get DeviceDriver Data by id (resource from MongoDB)
	 * @param id DeviceDriver Document id Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByID(String id, Class<T> cls){
		return deviceDriverRepoInterface.getData(id_str, id, cls); 
	}
	
	/**
	 * get DeviceDriver Data by name (resource from MongoDB)
	 * @param name DeviceDriver Document name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByName(String name, Class<T> cls){
		return deviceDriverRepoInterface.getData(name_str, name, cls);
	}
	
	/**
	 * get DeviceDriver Data List by name (resource from MongoDB)
	 * @param name DeviceDriver Documents name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> List<T> getDataListByName(String name, Class<T> cls){
		return deviceDriverRepoInterface.getDataList(name_str, name, cls);  
	}
	
}
