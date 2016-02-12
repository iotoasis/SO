package com.pineone.icbms.so.iot.provider;

import java.util.List;
import com.pineone.icbms.so.interfaces.repository.PhysicalDeviceRepoInterface;

public class PhysicalDeviceProvider extends AGeneralProvider {

	private static PhysicalDeviceRepoInterface physicalDeviceRepoInterface;

	/**
	 * 
	 * @param cl ModelObject Class
	 */
	public PhysicalDeviceProvider(){
		if(physicalDeviceRepoInterface == null){
			physicalDeviceRepoInterface = new PhysicalDeviceRepoInterface();
		}
	}
	
	/**
	 * get PhysicalDevice Data by id (resource from MongoDB)
	 * @param id PhysicalDevice Document id Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByID(String id, Class<T> cls){
		return physicalDeviceRepoInterface.getData(id_str, id, cls); 
	}
	
	/**
	 * get PhysicalDevice Data by name (resource from MongoDB)
	 * @param name PhysicalDevice Document name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByName(String name, Class<T> cls){
		return physicalDeviceRepoInterface.getData(name_str, name, cls);
	}
	
	/**
	 * get PhysicalDevice Data List by name (resource from MongoDB)
	 * @param name PhysicalDevice Documents name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> List<T> getDataListByName(String name, Class<T> cls){
		return physicalDeviceRepoInterface.getDataList(name_str, name, cls);  
	}
	
	/**
	 * get PhysicalDevice Data by uri (resource from MongoDB)
	 * @param uri PhysicalDevice Object uri
	 * @param cls return modelObjectClass
	 * @return
	 */
	public <T> T getDataByURI(String uri, Class<T> cls){
		return physicalDeviceRepoInterface.getData("uri", uri, cls);
	}
	
	/**
	 * get PhysicalDevice Data by 'deviceInformation ID' (resource from MongoDB)
	 * @param deviceinfo_id DeviceInformation Object ID 
	 * @param cls return modelObjectClass
	 * @return
	 */
	public <T> T getDataByDeviceInfomationID(String deviceInformationId, Class<T> cls){
		return physicalDeviceRepoInterface.getData("deviceInformation", deviceInformationId, cls);
	}
	
}
