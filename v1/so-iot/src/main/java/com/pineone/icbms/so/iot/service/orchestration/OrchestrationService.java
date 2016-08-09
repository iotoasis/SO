package com.pineone.icbms.so.iot.service.orchestration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.pineone.icbms.so.iot.creator.DefaultServiceCreator;
import com.pineone.icbms.so.iot.provider.OccurrenceProvider;
import com.pineone.icbms.so.iot.resources.model.repo.occurrence.OccurrenceModel;
import com.pineone.icbms.so.iot.resources.occurrence.DefaultOccurrence;
import com.pineone.icbms.so.iot.resources.service.ServiceSketch;
import com.pineone.icbms.so.iot.rule.RuleProcessor;
import com.pineone.icbms.so.iot.servicerunner.EmitService;
import com.pineone.icbms.so.iot.servicerunner.ServiceRunner;
import com.pineone.icbms.so.resources.domain.DefaultDomain;
import com.pineone.icbms.so.resources.service.IGenericService;
import com.withwiz.jellyfish.service.DefaultService;

/**
 * Orchestration service.<BR/>
 * Created by uni4love on 2015. 10. 29..
 */
public final class OrchestrationService extends
		DefaultService<OrchestrationServiceRequestDeliveryMessage, OrchestrationServiceResponseDeliveryMessage>
{
	static{
		ServiceRunner.getInstance().start();
	}
	/**
	 * service parameter key: RESPONSE_STATUS
	 */
	public static final String KEY_RESPONSE_STATUS = "RESPONSE_STATUS";

	/**
	 * service parameter key: METHOD
	 */
	public static final String KEY_METHOD = "METHOD";

	/**
	 * service parameter key: OCCURRENCE
	 */
	public static final String KEY_OCCURRENCE = "OCCURRENCE";

	/**
	 * service parameter key: SERVICE_SKETCH
	 */
	public static final String KEY_SERVICE_SKETCH = "SERVICE_SKETCH";

	/**
	 * service parameter key: SERVICE_MODEL_LIST
	 */
	public static final String KEY_SERVICE_MODEL_LIST = "SERVICE_MODEL_LIST";

	/**
	 * service parameter key: SERVICE_LIST
	 */
	public static final String KEY_SERVICE_LIST = "SERVICE_LIST";

	/**
	 * service parameter key: DOMAIN_LIST
	 */
	public static final String KEY_DOMAIN_LIST = "DOMAIN_LIST";

	/**
	 * service parameter value: success
	 */
	public static final int VALUE_STATUS_SUCCESS = 200;

	/**
	 * service parameter value: Resource NOT found
	 */
	public static final int VALUE_STATUS_NOT_FOUND = 404;

	/**
	 * service parameter value: VALUE_METHOD_INITIALIZE
	 */
	public static final String VALUE_METHOD_INITIALIZE = "VALUE_METHOD_INITIALIZE";

	/**
	 * service parameter value: METHOD_RULE_PROCESSING
	 */
	public static final String VALUE_METHOD_RULE_PROCESSING = "METHOD_RULE_PROCESSING";

	/**
	 * service parameter value: CREATE_SERVICES
	 */
	public static final String VALUE_METHOD_CREATE_SERVICES = "METHOD_CREATE_SERVICES";

	/**
	 * service parameter value: EXECUTE_A_SERVICE
	 */
	public static final String VALUE_METHOD_EXECUTE_SERVICES = "METHOD_EXECUTE_SERVICES";

	/**
	 * service parameter value: OCCUR_A_EVENT
	 */
	public static final String VALUE_METHOD_OCCUR_A_EVENT = "METHOD_OCCUR_A_EVENT";

	/**
	 * service name
	 */
	private static final String SERVICE_NAME = "Orchestration service center";

	/**
	 * constructor
	 */
	public OrchestrationService()
	{
		this.name = SERVICE_NAME;
		initialize();
	}

	/**
	 * OrchestrationService singleton instance holder
	 */
	private static final class SingletonHolder
	{
		static final OrchestrationService instance = new OrchestrationService();
	}

	/**
	 * return OrchestrationService singleton instance.<BR/>
	 *
	 * @return OrchestrationService instance
	 */
	public static OrchestrationService getInstance()
	{
		return SingletonHolder.instance;
	}

	/**
	 * initialize.<BR/>
	 */
	private void initialize()
	{
		// add services to service registry.

		// initialize service runner
		EmitService.getInstance();

	}

	/**
	 * occurrence event to database.<BR/>
	 * 
	 * @param occurrenceModel
	 *            occurrence model
	 */
	private void setOccurrenceEventToDb(OccurrenceModel occurrenceModel)
	{
		OccurrenceProvider provider = new OccurrenceProvider();
		Map<String, String> res = provider.setData(occurrenceModel);
		String result = res.get("result");
		String desc = res.get("desc");
	}

	/**
	 * process for a event occurred.<BR/>
	 * 
	 * @param occurrence
	 *            occurrence
	 */
	private void occurred(DefaultOccurrence occurrence)
	{
		// insert occurrence to database.
		OccurrenceModel model = new OccurrenceModel();
		model.setId(occurrence.getId());
		model.setTime(occurrence.getTime());
		model.setCmd(occurrence.getCmd());
		model.setContextId(occurrence.getContextId());
		model.setDomainList(occurrence.getDomainList());
		setOccurrenceEventToDb(model);
		// handler
		new OccurrenceHandler(occurrence).start();
	}

	/**
	 * Process rule.<BR/>
	 * 
	 * @param occurrence
	 *            occurrence
	 * @return service model list
	 */
	private ServiceSketch processRule(DefaultOccurrence occurrence)
	{
		RuleProcessor ruleProcessor = new RuleProcessor();
		ServiceSketch serviceSketch = ruleProcessor.executeRule(occurrence);
		return serviceSketch;
	}

	/**
	 * Create services.<BR/>
	 * 
	 * @param serviceSketch
	 *            service sketch
	 * @return service list
	 */
	private List<IGenericService> createServices(ServiceSketch serviceSketch)
	{
		DefaultServiceCreator sc = new DefaultServiceCreator();
		List<IGenericService> serviceList = sc.createServices(serviceSketch);
		return serviceList;
	}

	/**
	 * execute a service.<BR/>
	 * 
	 * @param serviceList
	 *            service list
	 */
	private void executeServices(List<IGenericService> serviceList)
	{
		Iterator<IGenericService> iter = serviceList.iterator();
		while (iter.hasNext())
		{
			// call ServiceRunner
			EmitService.getInstance().addService(iter.next());
		}
	}

	@Override
	public void onService(OrchestrationServiceRequestDeliveryMessage request,
			OrchestrationServiceResponseDeliveryMessage response)
	{
		String method = (String) request.getValue(KEY_METHOD);
		switch (method)
		{
			case VALUE_METHOD_OCCUR_A_EVENT:
				DefaultOccurrence occurrence = (DefaultOccurrence) request
						.getValue(KEY_OCCURRENCE);
				occurred(occurrence);
				response.addValue(KEY_RESPONSE_STATUS, "");
				response.addValue(KEY_RESPONSE_STATUS, VALUE_STATUS_SUCCESS);
				break;
			case VALUE_METHOD_RULE_PROCESSING:
				DefaultOccurrence occurrence2 = (DefaultOccurrence) request
						.getValue(KEY_OCCURRENCE);
				ServiceSketch serviceSketch = processRule(occurrence2);
				if (serviceSketch == null)
				{
					response.addValue(KEY_RESPONSE_STATUS,
							VALUE_STATUS_NOT_FOUND);
				}
				else
				{
					response.addValue(KEY_SERVICE_SKETCH, serviceSketch);
					response.addValue(KEY_RESPONSE_STATUS,
							VALUE_STATUS_SUCCESS);
				}
				break;
			case VALUE_METHOD_CREATE_SERVICES:
				ServiceSketch serviceSketch1 = (ServiceSketch) request
						.getValue(KEY_SERVICE_SKETCH);
				List<IGenericService> serviceList = createServices(
						serviceSketch1);
				response.addValue(KEY_SERVICE_LIST, serviceList);
				response.addValue(KEY_RESPONSE_STATUS, VALUE_STATUS_SUCCESS);
				break;
			case VALUE_METHOD_EXECUTE_SERVICES:
				List<IGenericService> serviceList1 = (List<IGenericService>) request
						.getValue(KEY_SERVICE_LIST);
				executeServices(serviceList1);
				response.addValue(KEY_RESPONSE_STATUS, VALUE_STATUS_SUCCESS);
				break;
			case VALUE_METHOD_INITIALIZE:
				initialize();
				response.addValue(KEY_RESPONSE_STATUS, VALUE_STATUS_SUCCESS);
				break;
		}
	}

	/**
	 * test main.<BR/>
	 * 
	 * @param args
	 *            parameters
	 */
	public static void main(String[] args)
	{
		String testContextID = "CM-1-1-110";
		String classRoomURI = "xxx.xxx.xx/xx/xx/classroom101";
		String testURI = "/resource/occ";

		// domain
		DefaultDomain domain = new DefaultDomain();
		domain.setId(classRoomURI);
		// domain list
		ArrayList<DefaultDomain> domains = new ArrayList<>();
		domains.add(domain);
		// occurrence
		DefaultOccurrence occurrence = new DefaultOccurrence("occ",
				testContextID, domains, "20151110T153028");

		OrchestrationServiceRequestDeliveryMessage req = null;
		OrchestrationServiceResponseDeliveryMessage res = null;
		OrchestrationService service = null;
		int status;
		// --------------------- occurred
		// request message
		req = new OrchestrationServiceRequestDeliveryMessage();
		// set method
		req.addValue(KEY_METHOD, VALUE_METHOD_OCCUR_A_EVENT);
		// set a occurrence
		req.addValue(KEY_OCCURRENCE, occurrence);
		// response message
		res = new OrchestrationServiceResponseDeliveryMessage();
		// service
		service = new OrchestrationService();
		// execute service
		service.onService(req, res);
		// get response status
		status = (int) res.getValue(KEY_RESPONSE_STATUS);

		// --------------------- rule processing
		req = new OrchestrationServiceRequestDeliveryMessage();
		// set method
		req.addValue(KEY_METHOD, VALUE_METHOD_RULE_PROCESSING);
		// set a occurrence
		req.addValue(KEY_OCCURRENCE, occurrence);
		// response message
		res = new OrchestrationServiceResponseDeliveryMessage();
		// service
		service = new OrchestrationService();
		// execute service
		service.onService(req, res);
		// get response status
		status = (int) res.getValue(KEY_RESPONSE_STATUS);
		ServiceSketch serviceSketch = null;
		if (status == OrchestrationService.VALUE_STATUS_SUCCESS)
		{
			// get service sketch
			serviceSketch = (ServiceSketch) res.getValue(KEY_SERVICE_SKETCH);
		}
		// --------------------- create services
		req = new OrchestrationServiceRequestDeliveryMessage();
		// set method
		req.addValue(KEY_METHOD, VALUE_METHOD_CREATE_SERVICES);
		// set service sketch
		req.addValue(KEY_SERVICE_SKETCH, serviceSketch);
		// response message
		res = new OrchestrationServiceResponseDeliveryMessage();
		// service
		service = new OrchestrationService();
		// execute service
		service.onService(req, res);
		// get response status
		status = (int) res.getValue(KEY_RESPONSE_STATUS);
		// get service list
		List<IGenericService> serviceList = null;
		if (status == OrchestrationService.VALUE_STATUS_SUCCESS)
		{
			serviceList = (List<IGenericService>) res
					.getValue(KEY_SERVICE_LIST);
		}
		// --------------------- execute services
		req = new OrchestrationServiceRequestDeliveryMessage();
		// set method
		req.addValue(KEY_METHOD, VALUE_METHOD_EXECUTE_SERVICES);
		// set service list
		req.addValue(KEY_SERVICE_LIST, serviceList);
		// response message
		res = new OrchestrationServiceResponseDeliveryMessage();
		// service
		service = new OrchestrationService();
		// execute service
		service.onService(req, res);
		// get response status
		status = (int) res.getValue(KEY_RESPONSE_STATUS);
		if (status == OrchestrationService.VALUE_STATUS_SUCCESS)
		{
		}
	}

	/**
	 * Occurrence handler class.<BR/>
	 */
	class OccurrenceHandler extends Thread
	{
		/**
		 * occurrence
		 */
		private DefaultOccurrence occurrence = null;

		/**
		 * contructor
		 * 
		 * @param occurrence
		 *            occurrence
		 */
		public OccurrenceHandler(DefaultOccurrence occurrence)
		{
			this.occurrence = occurrence;
		}

		@Override
		public void run()
		{
			occurrenceHandler(occurrence);
		}

		/**
		 * occurrence handler.<BR/>
		 *
		 * @param occurrence
		 *            occurrence
		 */
		private void occurrenceHandler(DefaultOccurrence occurrence)
		{
			int status;
			OrchestrationServiceRequestDeliveryMessage req = null;
			OrchestrationServiceResponseDeliveryMessage res = null;
			OrchestrationService service = null;
			// --------------------- rule processing
			req = new OrchestrationServiceRequestDeliveryMessage();
			// set method
			req.addValue(KEY_METHOD, VALUE_METHOD_RULE_PROCESSING);
			// set a occurrence
			req.addValue(KEY_OCCURRENCE, occurrence);
			// response message
			res = new OrchestrationServiceResponseDeliveryMessage();
			// service
			service = new OrchestrationService();
			// execute service
			service.onService(req, res);
			// get response status
			status = (int) res.getValue(KEY_RESPONSE_STATUS);
			ServiceSketch serviceSketch = null;
			if (status == OrchestrationService.VALUE_STATUS_SUCCESS)
			{
				// get service sketch
				serviceSketch = (ServiceSketch) res
						.getValue(KEY_SERVICE_SKETCH);
			}
			// --------------------- create services
			req = new OrchestrationServiceRequestDeliveryMessage();
			// set method
			req.addValue(KEY_METHOD, VALUE_METHOD_CREATE_SERVICES);
			// set service sketch
			req.addValue(KEY_SERVICE_SKETCH, serviceSketch);
			// response message
			res = new OrchestrationServiceResponseDeliveryMessage();
			// service
			service = new OrchestrationService();
			// execute service
			service.onService(req, res);
			// get response status
			status = (int) res.getValue(KEY_RESPONSE_STATUS);
			// get service list
			List<IGenericService> serviceList = null;
			if (status == OrchestrationService.VALUE_STATUS_SUCCESS)
			{
				serviceList = (List<IGenericService>) res
						.getValue(KEY_SERVICE_LIST);
			}
			// --------------------- execute services
			req = new OrchestrationServiceRequestDeliveryMessage();
			// set method
			req.addValue(KEY_METHOD, VALUE_METHOD_EXECUTE_SERVICES);
			// set service list
			req.addValue(KEY_SERVICE_LIST, serviceList);
			// response message
			res = new OrchestrationServiceResponseDeliveryMessage();
			// service
			service = new OrchestrationService();
			// execute service
			service.onService(req, res);
			// get response status
			status = (int) res.getValue(KEY_RESPONSE_STATUS);
			if (status == OrchestrationService.VALUE_STATUS_SUCCESS)
			{
			}
		}
	}
}
