package com.pineone.icbms.so.iot.provider;

import java.util.List;
import java.util.Map;

import com.pineone.icbms.so.interfaces.repository.OccurrenceRepoInterface;

public class OccurrenceProvider extends AGeneralProvider {

	private static OccurrenceRepoInterface occurrenceRepoInterface;

	/**
	 * 
	 * @param cl ModelObject Class
	 */
	public OccurrenceProvider(){
		if(occurrenceRepoInterface == null){
			occurrenceRepoInterface = new OccurrenceRepoInterface();
		}
	}
	
	/**
	 * get Occurrence Data by id (resource from MongoDB)
	 * @param id Occurrence Document id Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByID(String id, Class<T> cls){
		return occurrenceRepoInterface.getData(id_str, id, cls); 
	}
	
	/**
	 * get Occurrence Data by name (resource from MongoDB)
	 * @param name Occurrence Document name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByName(String name, Class<T> cls){
		return occurrenceRepoInterface.getData(name_str, name, cls);
	}
	
	/**
	 * get Occurrence Data List by name (resource from MongoDB)
	 * @param name Occurrence Documents name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> List<T> getDataListByName(String name, Class<T> cls){
		return occurrenceRepoInterface.getDataList(name_str, name, cls);  
	}
	
	/**
	 * set Occurrence Data
	 * @param data Occurrence Object
	 * @return Occurrence insert result
	 */
	public <T, D> Map<String, String> setData(D data){
		setModelDate(data,"setCreatedDate");
		setModelDate(data,"setModifiedDate");
		return occurrenceRepoInterface.setData(data); 
	}
	
}
