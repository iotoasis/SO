package com.pineone.icbms.so.iot.creator;

import com.pineone.icbms.so.iot.provider.ServiceProvider;
import com.pineone.icbms.so.iot.resources.service.ServiceSketch;
import com.pineone.icbms.so.resources.domain.AGenericDomain;
import com.pineone.icbms.so.resources.model.repo.service.DefaultServiceModel;
import com.pineone.icbms.so.resources.model.repo.task.DefaultTaskModel;
import com.pineone.icbms.so.resources.service.DefaultService;
import com.pineone.icbms.so.resources.service.IGenericService;
import com.pineone.icbms.so.resources.task.IGenericTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by existmaster on 2016. 1. 4..
 */
public class DefaultServiceCreator
{
	/**
	 * Create Service by ID
	 * 
	 *
	 * @param serviceModelId
	 * @param domainType
	 *            domainType
	 * @param domain
	 * @return IGenericService
	 */
	public IGenericService createServiceByID(String serviceModelId, String domainType, AGenericDomain domain)
	{
		ServiceProvider serviceProvider = new ServiceProvider();
		DefaultServiceModel defaultServiceModel = serviceProvider
				.getDataByID(serviceModelId, DefaultServiceModel.class);

		return createService(defaultServiceModel, domainType, domain);
	}

	/**
	 * Create Service
	 * 
	 * @param defaultServiceModel
	 *            defaultServiceModel
	 *
	 * @param domainType
	 * @param domain
	 * @return IGenericService
	 */
	public IGenericService createService(
			DefaultServiceModel defaultServiceModel, String domainType, AGenericDomain domain)
	{
		DefaultService<Object> defaultService = new DefaultService<Object>();

		defaultService.addValue(domainType, domain);

		DefaultTaskCreator defaultTaskCreator = new DefaultTaskCreator();
		defaultService
				.setId(defaultServiceModel.getId() + "_" + System.nanoTime());
		defaultService.setName(defaultServiceModel.getName());

		List<DefaultTaskModel> taskModelList = defaultServiceModel
				.getTaskModelList();
		List<IGenericTask> tasks = defaultTaskCreator
				.createTasks(taskModelList, defaultService);



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
		String domainType = serviceSketch.getDomainType();


		for (String serviceModelId : serviceSketch.getServiceModelIdList())
		{
			for (AGenericDomain domain : serviceSketch.getDomainList())
			{

				DefaultService defaultService = (DefaultService) createServiceByID(
						serviceModelId, domainType, domain);

				defaultServiceList.add(defaultService);
			}
		}

		return defaultServiceList;
	}
}
