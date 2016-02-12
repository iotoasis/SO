package com.pineone.icbms.so.iot.provider;

import java.util.List;
import com.pineone.icbms.so.interfaces.repository.ContextRepoInterface;

public class ContextProvider extends AGeneralProvider {

	private static ContextRepoInterface contextRepoInterface;

	/**
	 * 
	 * @param cl ModelObject Class
	 */
	public ContextProvider(){
		if(contextRepoInterface == null){
			contextRepoInterface = new ContextRepoInterface();
		}
	}
	
	/**
	 * get TaskModal Data by id (resource from MongoDB)
	 * @param id TaskModal Document id Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByID(String id, Class<T> cls){
		return contextRepoInterface.getData(id_str, id, cls); 
	}
	
	/**
	 * get TaskModal Data by name (resource from MongoDB)
	 * @param name TaskModal Document name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByName(String name, Class<T> cls){
		return contextRepoInterface.getData(name_str, name, cls);
	}
	
	/**
	 * get TaskModal Data List by name (resource from MongoDB)
	 * @param name TaskModal Documents name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> List<T> getDataListByName(String name, Class<T> cls){
		return contextRepoInterface.getDataList(name_str, name, cls);  
	}
	
}
