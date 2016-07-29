package com.pineone.icbms.so.util;

import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.iot.service.action.ExecuteDriverAction;
import com.pineone.icbms.so.iot.service.action.SearchDeviceDriverAction;
import com.pineone.icbms.so.resources.context.DefaultContext;
import com.pineone.icbms.so.resources.property.operation.DefaultOperationValue;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;
import com.pineone.icbms.so.server.config.WebAppContext;
import com.pineone.icbms.so.util.restful.ClientService;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by use on 2015-11-02.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppContext.class)
@WebAppConfiguration
public class ClientServiceTest
{
	ClientService clientService = new ClientService();


	@Test
	public void clientServiceRequestpostTest()
	{

		/**
		 * Prepare Resourcesdb.properties http://httpbin.org 공용 dummy서버에서 테스트 사용
		 */

		/**
		 * Process
		 */
		IHttpResponseMessage response = clientService.requestPostService(
				"http://166.104.112.34:8081/si/command", "{\"_uri\":\"/herit-in/herit-cse/Aircon_LR0001AC0001\",\"_notificationUri\":\"http://219.248.137.7:10080/so/resources/vdcm/cmd_1452849235157\",\"_commandId\":\"cmd_1452849235157\",\"_command\":\"action\",\"cnf\":\"text/plain:0\",\"con\":\"ON\"}");
		System.out.println("body =" + response.getBodyByteArray());
		/*IHttpResponseMessage response = clientService.requestPostService(
				"http://166.104.112.42:10080/so/resource/occ1", "{\"cmd\":\"occ\",\"contextId\":\"CM-1-1-110\",\"domains\":[{\"id\": \"http://www.pineone.com/campus/LR0001\"}],\"time\":\"20151020T153028\"}");*/

		/**
		 * Expect Result
		 */

		assertEqualsResponse(response);

	}


	@Test
	public void clientServiceRequestgetTest()

	{
		/**
		 * Prepare Resources
		 */

		/**
		 * Process
		 */
		IHttpResponseMessage response = clientService
				.requestGetService("http://httpbin.org/get");

		System.out.println(
				"$response$ = $" + new String(response.getBodyByteArray()));

		/**
		 * Expect Result
		 */
		assertEqualsResponse(response);
	}

	public void assertEqualsResponse(IHttpResponseMessage response)
	{
		assertEquals(response.getStatusCode(), 200);
	}


	@Test
	public void deviceControlTest()
	{
		ExecuteDriverAction executeDriverAction = new ExecuteDriverAction();
		SearchDeviceDriverAction searchDeviceDriverAction =  new SearchDeviceDriverAction();
		DefaultContext defaultContext = new DefaultContext();
		List<DefaultPhysicalDevice> defaultPhysicalDevices = new ArrayList<>();
		DefaultPhysicalDevice device = new DefaultPhysicalDevice();
		device.setUri("http://www.pineone.com/herit-in/herit-cse/Aircon_LR0001AC0001");
		defaultPhysicalDevices.add(device);
		device = new DefaultPhysicalDevice();
		device.setUri("http://www.pineone.com/herit-in/herit-cse/Aircon_LR0001AC0002");
		defaultPhysicalDevices.add(device);
		device = new DefaultPhysicalDevice();
		device.setUri("http://www.pineone.com/herit-in/herit-cse/Aircon_DT0001AC0001");
		defaultPhysicalDevices.add(device);
		defaultContext.addValue(IotServiceContext.ACTION_DEVICE_URI,defaultPhysicalDevices);

		DefaultOntologyReference ontologyReference = new DefaultOntologyReference();
		DefaultOperationValue defaultOperationValue = new DefaultOperationValue();

		ontologyReference.setId(IGenericOntologyReference.REF_AIR_COOLING_CONTROL);
//		ontologyReference.setReference(IGenericOntologyReference.REF_AIR_COOLING_CONTROL);
		defaultOperationValue.setOperationValue("ON");

		defaultContext.addValue(IotServiceContext.ACTION_OPERATION_VALUE,defaultOperationValue);
		defaultContext.addValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE,ontologyReference);


		searchDeviceDriverAction.execute(defaultContext);
		System.out.println("ontoddd " + defaultContext.getValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE));
		executeDriverAction.execute(defaultContext);

	}

}
