package com.pineone.icbms.so.iot.provider;

import java.util.List;
import com.pineone.icbms.so.interfaces.repository.TaskRepoInterface;;

public class TaskProvider extends AGeneralProvider {

	private static TaskRepoInterface taskRepoInterface;

	/**
	 * 
	 * @param cl ModelObject Class
	 */
	public TaskProvider(){
		if(taskRepoInterface == null){
			taskRepoInterface = new TaskRepoInterface();
		}
	}
	
	/**
	 * get TaskModal Data by id (resource from MongoDB)
	 * @param id TaskModal Document id Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByID(String id, Class<T> cls){
		return taskRepoInterface.getData(id_str, id, cls); 
	}
	
	/**
	 * get TaskModal Data by name (resource from MongoDB)
	 * @param name TaskModal Document name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByName(String name, Class<T> cls){
		return taskRepoInterface.getData(name_str, name, cls);
	}
	
	/**
	 * get TaskModal Data List by name (resource from MongoDB)
	 * @param name TaskModal Documents name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> List<T> getDataListByName(String name, Class<T> cls){
		return taskRepoInterface.getDataList(name_str, name, cls);  
	}
	
}
