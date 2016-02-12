package com.pineone.icbms.so.iot.creator;

import java.util.ArrayList;
import java.util.List;

import com.pineone.icbms.so.iot.provider.ServiceProvider;
import com.pineone.icbms.so.iot.resources.service.ServiceSketch;
import com.pineone.icbms.so.resources.domain.DefaultDomain;
import com.pineone.icbms.so.resources.model.repo.service.DefaultServiceModel;
import com.pineone.icbms.so.resources.model.repo.task.DefaultTaskModel;
import com.pineone.icbms.so.resources.processor.IServiceCreator;
import com.pineone.icbms.so.resources.service.DefaultService;
import com.pineone.icbms.so.resources.service.IGenericService;
import com.pineone.icbms.so.resources.task.IGenericTask;

/**
 * Created by existmaster on 2016. 1. 4..
 */
public class DefaultServiceCreator implements IServiceCreator
{
	/**
	 * Create Service by ID
	 * 
	 * @param serviceModelID
	 *            serviceModelID
	 * @return IGenericService
	 */
	public IGenericService createServiceByID(String serviceModelID)
	{
		ServiceProvider serviceProvider = new ServiceProvider();
		DefaultServiceModel defaultServiceModel = serviceProvider
				.getDataByID(serviceModelID, DefaultServiceModel.class);

		return createService(defaultServiceModel);
	}

	/**
	 * Create Service
	 * 
	 * @param defaultServiceModel
	 *            defaultServiceModel
	 * 
	 * @return IGenericService
	 */
	public IGenericService createService(
			DefaultServiceModel defaultServiceModel)
	{
		DefaultService<Object> defaultService = new DefaultService<Object>();
		DefaultTaskCreator defaultTaskCreator = new DefaultTaskCreator();
		defaultService
				.setId(defaultServiceModel.getId() + "_" + System.nanoTime());
		defaultService.setName(defaultServiceModel.getName());

		List<DefaultTaskModel> taskModelList = defaultServiceModel
				.getTaskModelList();
		List<IGenericTask> tasks = defaultTaskCreator
				.createTasks(taskModelList);
		defaultService.setTaskList(tasks);

		return defaultService;
	}

	/**
	 * Create Services
	 * 
	 * @param serviceSketch
	 *            serviceSketch
	 * @return List of IGenericService
	 */
	public List<IGenericService> createServices(ServiceSketch serviceSketch)
	{
		List<IGenericService> defaultServiceList = new ArrayList<IGenericService>();
		String domain_Type = serviceSketch.getDomainType();


		for (String serviceModelId : serviceSketch.getServiceModelIdList())
		{
			for (DefaultDomain defaultDomain : serviceSketch.getDomainList())
			{

				DefaultService defaultService = (DefaultService) createServiceByID(
						serviceModelId);
				System.out.println(String.format("[[Service AddValue]] -- K(Domain_Type) : %s, V(defualtDomain) : %s", domain_Type, defaultDomain.toString()));
				defaultService.addValue(domain_Type, defaultDomain);
				defaultServiceList.add(defaultService);
			}
		}

		return defaultServiceList;
	}
}
