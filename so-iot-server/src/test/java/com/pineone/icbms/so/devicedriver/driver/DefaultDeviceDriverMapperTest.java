package com.pineone.icbms.so.devicedriver.driver;

import com.pineone.icbms.so.iot.devicedriver.DefaultDeviceDriverMapper;
import com.pineone.icbms.so.iot.provider.DeviceDriverProvider;
import com.pineone.icbms.so.iot.provider.DeviceInformationProvider;
import com.pineone.icbms.so.iot.provider.PhysicalDeviceProvider;
import com.pineone.icbms.so.iot.resources.model.repo.device.DeviceInformationModel;
import com.pineone.icbms.so.iot.resources.model.repo.device.PhysicalDeviceModel;
import com.pineone.icbms.so.iot.resources.model.repo.driver.DeviceDriverModel;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.server.config.WebAppContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.Assert.assertEquals;

/**
 * Created by pahnj on 2016-01-11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppContext.class)
@WebAppConfiguration
public class DefaultDeviceDriverMapperTest
{

	PhysicalDeviceProvider		physicalDeviceProvider		= new PhysicalDeviceProvider();
	DeviceInformationProvider	deviceInformationProvider	= new DeviceInformationProvider();
	DeviceDriverProvider		deviceDriverProvider		= new DeviceDriverProvider();

	PhysicalDeviceModel		physicalDeviceModel		= new PhysicalDeviceModel();
	DeviceInformationModel	deviceInformationModel	= new DeviceInformationModel();
	DeviceDriverModel		deviceDriverModel		= new DeviceDriverModel();

	@Test
	public void physicalDeviceProviderTest()
	{
		physicalDeviceModel = physicalDeviceProvider.getDataByURI(
				"http://www.pineone.com/herit-in/herit-cse/Aircon_LR0001AC0001",
				PhysicalDeviceModel.class);
		assertEquals(physicalDeviceModel.getId(), "1501");
	}

	@Test
	public void deviceInformationProviderTest()
	{
		deviceInformationModel = deviceInformationProvider.getDataByID("1201",
				DeviceInformationModel.class);
		assertEquals(deviceInformationModel.getId(), "1201");
	}

	@Test
	public void deviceDriverProviderTest()
	{
		deviceDriverModel = deviceDriverProvider.getDataByID("1001",
				DeviceDriverModel.class);
		assertEquals(deviceDriverModel.getId(), "1001");
	}

	@Test
	public void allProviderTest()
	{
		physicalDeviceModel = physicalDeviceProvider.getDataByURI(
				"http://www.pineone.com/herit-in/herit-cse/Aircon_LR0001AC0001",
				PhysicalDeviceModel.class);
		deviceInformationModel = deviceInformationProvider.getDataByID(
				physicalDeviceModel.getDeviceInformationId(),
				DeviceInformationModel.class);
		deviceDriverModel = deviceDriverProvider.getDataByID(
				deviceInformationModel.getDeviceDriverId(),
				DeviceDriverModel.class);
		assertEquals(deviceDriverModel.getId(), "1001");
	}

	@Test
	public void providerDeviceDriverTest()
	{
		DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();
		physicalDevice.setUri("http://www.pineone.com/herit-in/herit-cse/Aircon_LR0001AC0001");
		DefaultDeviceDriverMapper mapper = new DefaultDeviceDriverMapper();
		mapper.providerDeviceDriver(physicalDevice);

	}

}
