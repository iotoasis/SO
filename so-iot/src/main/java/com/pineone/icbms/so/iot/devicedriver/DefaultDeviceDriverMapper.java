package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.provider.DeviceDriverProvider;
import com.pineone.icbms.so.iot.provider.DeviceInformationProvider;
import com.pineone.icbms.so.iot.provider.PhysicalDeviceProvider;
import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.driver.IGenericDeviceDriver;
import com.pineone.icbms.so.iot.resources.driver.mapper.AGenericDeviceDriverMapper;
import com.pineone.icbms.so.iot.resources.model.repo.device.DeviceInformationModel;
import com.pineone.icbms.so.iot.resources.model.repo.device.PhysicalDeviceModel;
import com.pineone.icbms.so.iot.resources.model.repo.driver.DeviceDriverModel;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Device driver mapper default class.<BR/>
 * Created by uni4love on 2015. 11. 28..
 */
public class DefaultDeviceDriverMapper extends
		AGenericDeviceDriverMapper<IGenericDeviceDriver, IGenericDeviceContext>
{

	private final Logger log = LoggerFactory.getLogger(DefaultDeviceDriverMapper.class);

	/**
	 * Physical Device Provider<BR/>
	 */
	PhysicalDeviceProvider		physicalDeviceProvider		= new PhysicalDeviceProvider();
	/**
	 * Device Information Provider<BR/>
	 */
	DeviceInformationProvider	deviceInformationProvider	= new DeviceInformationProvider();
	/**
	 * Device Driver Provider<BR/>
	 */
	DeviceDriverProvider		deviceDriverProvider		= new DeviceDriverProvider();

	/**
	* Physical Device Model<BR/>
	 */
	PhysicalDeviceModel		physicalDeviceModel		= new PhysicalDeviceModel();
	/**
	 * Device Information Model<BR/>
	 */
	DeviceInformationModel	deviceInformationModel	= new DeviceInformationModel();
	/**
	 * Device Driver Model<BR/>
	 */
	DeviceDriverModel		deviceDriverModel		= new DeviceDriverModel();

	/**
	 * Device Driver LIst<BR/>
	 */
	List<DefaultDeviceDriver>  deviceDriverList = new ArrayList<DefaultDeviceDriver>();

	/**
	 * Bring the servicecontext to make the devices<BR/>
	 * @param device serviceContext
     */
	public void getDeviceDrivers(IGenericDeviceContext device)
	{
		log.info("DeviceDriverMapper getDeviceDrivers");
		/**
		 * init DefaultDeviceDriver List<BR/>
		 */
		if(deviceDriverList != null)
		{
			deviceDriverList = new ArrayList<DefaultDeviceDriver>();
		}

		/**
		 * ServiceContext to get a DefaultPhysicalDevice.<BR/>
		 * DefaultPhysicalDevices to get a DefaultDeviceDriver.<BR/>
		 */
		for(DefaultPhysicalDevice physicalDevice : (List<DefaultPhysicalDevice>)device.getValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_URI))
		{
			log.info("[[DefaultPhysicalDevice Data]] = " + physicalDevice.toString());
			providerDeviceDriver(physicalDevice);
		}

		/**
		 * Save the devicedriverlist the ServiceContext<BR/>.
		 */
		log.info("DeviceDriverMapper  save the deviceDriverList");
		device.addValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_DRIVER, deviceDriverList);

	}

	/**
	 * Bring to packageName the DeviceDirver.
	 * @param physicalDevice
     */
	public void providerDeviceDriver(DefaultPhysicalDevice physicalDevice) {
		log.info("DeviceDriverMapper providerDeviceDriver");
		/**
         * Have a physicalModel Device Device ID brings in the DB.<BR/>
         */
		log.info("DeviceDriverMapper get the physical Device Model");
		physicalDeviceModel = physicalDeviceProvider.getDataByURI(
                physicalDevice.getUri(), PhysicalDeviceModel.class);
		log.debug("DeviceDriverMapper physical Device Model data = " + physicalDeviceModel != null ? physicalDeviceModel.toString() : "null");
		/**
		 * With the identity of deviceInfomationModel leads to deviceInfomationModel in DB.<BR/>
         */
		log.info("DeviceDriverMapper get the Device Information Model");
		deviceInformationModel = deviceInformationProvider.getDataByID(
                physicalDeviceModel.getDeviceInformationId(),
                DeviceInformationModel.class);
		log.debug("DeviceDriverMapper Device Information Model data = " + deviceInformationModel != null ? deviceInformationModel.toString() : "null");
		/**
		 * With the identity of deviceDriverModel leads to deviceDriverModel in DB.<BR/>
         */
		log.info("DeviceDriverMapper get the Device Driver Model");
		deviceDriverModel = deviceDriverProvider.getDataByID(
                deviceInformationModel.getDeviceDriverId(),
                DeviceDriverModel.class);
		log.debug("DeviceDriverMapper Device Driver Model data = " + deviceDriverModel != null ? deviceDriverModel.toString() : "null");
		/**
		 * Bring to packageName the DeviceDirver.
         */

		DefaultDeviceDriver deviceDriver = null;
		try
        {
			log.info("DeviceDriverMapper packageName get the Device Driver");
            deviceDriver = (DefaultDeviceDriver) Class
                    .forName(deviceDriverModel.getPackageName()).newInstance();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
		deviceDriverList.add(deviceDriver);
	}

	@Override
	public IGenericDeviceDriver getDeviceDriver(IGenericDeviceContext device)
	{
		return null;
	}

}
