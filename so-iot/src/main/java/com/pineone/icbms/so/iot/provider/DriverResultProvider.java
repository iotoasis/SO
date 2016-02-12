package com.pineone.icbms.so.iot.provider;

import java.util.List;
import java.util.Map;

import com.pineone.icbms.so.interfaces.repository.DriverResultRepoInterface;

public class DriverResultProvider extends AGeneralProvider {

	private static DriverResultRepoInterface driverResultRepoInterface;

	/**
	 * 
	 * @param cl ModelObject Class
	 */
	public DriverResultProvider(){
		if(driverResultRepoInterface == null){
			driverResultRepoInterface = new DriverResultRepoInterface();
		}
	}
	
	/**
	 * get DeviceDriver Data by id (resource from MongoDB)
	 * @param id DeviceDriver Document id Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByID(String id, Class<T> cls){
		return driverResultRepoInterface.getData(id_str, id, cls); 
	}

	@Override
	public <T> T getDataByName(String name, Class<T> cls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getDataListByName(String name, Class<T> cls) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * set DriverResult Data
	 * @param data DriverResult Object
	 * @return DriverResult Object insert result
	 */
	public <T, D> Map<String, String> setData(D data){
		setModelDate(data,"setCreatedDate");
		return driverResultRepoInterface.setData(data); 
	}
	
	/**
	 * update DriverResult Data
	 * @param id DriverResult Document id Field Value
	 * @param data Update DriverResult Object
	 * @return DriverResult Object insert result
	 */
	public <T, D> Map<String, String> updateDataByID(String id, D data){
		setModelDate(data,"setModifiedDate");
		return driverResultRepoInterface.updateData(id_str, id, data); 
	}
	
}
