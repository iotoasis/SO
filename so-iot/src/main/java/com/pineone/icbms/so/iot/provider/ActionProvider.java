package com.pineone.icbms.so.iot.provider;

import java.util.List;
import com.pineone.icbms.so.interfaces.repository.ActionRepoInterface;

public class ActionProvider extends AGeneralProvider {

	private static ActionRepoInterface actionRepoInterface;

	/**
	 * 
	 * @param cl ModelObject Class
	 */
	public ActionProvider(){
		if(actionRepoInterface == null){
			actionRepoInterface = new ActionRepoInterface();
		}
	}
	
	/**
	 * get TaskModal Data by id (resource from MongoDB)
	 * @param id TaskModal Document id Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByID(String id, Class<T> cls){
		return actionRepoInterface.getData(id_str, id, cls); 
	}
	
	/**
	 * get TaskModal Data by name (resource from MongoDB)
	 * @param name TaskModal Document name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByName(String name, Class<T> cls){
		return actionRepoInterface.getData(name_str, name, cls);
	}
	
	/**
	 * get TaskModal Data List by name (resource from MongoDB)
	 * @param name TaskModal Documents name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> List<T> getDataListByName(String name, Class<T> cls){
		return actionRepoInterface.getDataList(name_str, name, cls);  
	}
	
}
