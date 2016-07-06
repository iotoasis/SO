package com.pineone.icbms.so.iot.provider;

import java.util.List;

import com.pineone.icbms.so.interfaces.repository.ProfileRepoInterface;;

public class ProfileProvider extends AGeneralProvider {

	private static ProfileRepoInterface profileRepoInterface;

	/**
	 * 
	 * @param cl ModelObject Class
	 */
	public ProfileProvider(){
		if(profileRepoInterface == null){
			profileRepoInterface = new ProfileRepoInterface();
		}
	}
	
	/**
	 * get ProfileModel Data by id (resource from MongoDB)
	 * @param id ProfileModel Document id Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByID(String id, Class<T> cls){
		return profileRepoInterface.getData(id_str, id, cls); 
	}
	
	/**
	 * get ProfileModel Data by name (resource from MongoDB)
	 * @param name ProfileModel Document name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> T getDataByName(String name, Class<T> cls){
		return profileRepoInterface.getData(name_str, name, cls);
	}
	
	/**
	 * get ProfileModel Data List by name (resource from MongoDB)
	 * @param name ProfileModel Documents name Field
	 * @param cls return modelObjectClass
	 */
	@Override
	public <T> List<T> getDataListByName(String name, Class<T> cls){
		return profileRepoInterface.getDataList(name_str, name, cls);  
	}
	
	/**
	 * get ProfileModel Data List by SubDocument(contextModelList contextModel ID Property)
	 * @param contextID ProfileModel Class contextModelList Item Property 
	 * @param cls return modelObjectClass
	 * @return ProfileModel Object List
	 */
	public <T> List<T> getDataListByContextID(String contextID, Class<T> cls){
		String type = profileRepoInterface.modelTYPE(cls);
		//String query = "{ $and:[{type:'"+type+"'},{'contextModelList':{$in:['"+contextID+"']}}]}";
		String query = "{ $and:[{type:'"+type+"'},{'contextModelList':{$elemMatch:{'id':'"+contextID+"'}}}]}";
		return profileRepoInterface.getDataListByQuery(query, cls);
	}
	
	/**
	 * get ProfileModel Data List by SubDocument(serviceModelList serviceModel ID Property)
	 * @param contextID ProfileModel Class serviceModelList Item Property 
	 * @param cls return modelObjectClass
	 * @return ProfileModel Object List
	 */
	public <T> List<T> getDataListByServiceID(String serviceID, Class<T> cls){
		String type = profileRepoInterface.modelTYPE(cls);
		String query = "{ $and:[{type:'"+type+"'},{'serviceModelList':{$elemMatch:{'id':'"+serviceID+"'}}}]}";
		return profileRepoInterface.getDataListByQuery(query, cls);
	}
	
}
