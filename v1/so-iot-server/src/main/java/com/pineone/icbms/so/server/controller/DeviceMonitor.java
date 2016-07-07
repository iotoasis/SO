package com.pineone.icbms.so.server.controller;

import com.pineone.icbms.so.iot.provider.DriverResultProvider;
import com.pineone.icbms.so.iot.resources.message.ResultMessage;
import com.pineone.icbms.so.iot.resources.model.repo.driver.result.DriverResultModel;
import com.pineone.icbms.so.iot.util.service.DataConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * DeviceMonitor Class<BR/>
 */
@RestController
public class DeviceMonitor
{

	private final Logger log = LoggerFactory.getLogger(DeviceMonitor.class);

	/**
	 * Receiving a response result determined whether the result.<BR/>
	 * 
	 * @param message
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/resources/vdcm/{id}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String asynchronousControlResult(@RequestBody ResultMessage message,
			@PathVariable String id) throws Exception
	{
		DriverResultProvider	driverResultProvider	= new DriverResultProvider();
		/**
		 * DB get DriverResultModel<BR/>
		 */
		DriverResultModel		driverResultModel		= null;
		/**
		 * Driver result2 data model<BR/>
		 */
		DriverResultModel		resultModel				= new DriverResultModel();

		log.info("insert device moniter controller");
		log.info("ResultMessage _commandId = " + message.get_commandId()
				+ " | resultCode = " + message.get_resultCode() + " | result = "
				+ message.get_result());

		System.out.println("[[DeviceMoniter message]] = " + message.toString());

		/**
		 * Save the Result2 Data<BR/>
		 */
		driverResultModel = driverResultProvider.getDataByID(id,
				DriverResultModel.class);

		if (driverResultModel != null
				&& (DataConversion.RESPONSE_SUCCESS_ONEM2MCODE
						.equals(message.get_resultCode())
						|| DataConversion.RESPONSE_SUCCESS_CODE
								.equals(message.get_resultCode())
						|| DataConversion.RESPONSE_SUCCESS
								.equals(message.get_resultCode())))
		{
			log.info("success resultData and the response has been properly.");
			log.debug(message.get_commandId() + " data success");
			resultModel.setResult2(message.get_resultCode());

			System.out.println("[[driverResultModel getId, getResult2]] = " + driverResultModel.getId() +", "+ driverResultModel.getResult2());

			driverResultProvider.updateDataByID(driverResultModel.getId(),
					resultModel);
		} else {
			if(driverResultModel != null)
			{
				log.info("fail resultData and the response has been properly.");
				log.debug(message.get_commandId() + " data fail");
				resultModel.setResult2(message.get_resultCode());

				System.out.println("[[driverResultModel getId, getResult2]] = " + driverResultModel.getId() +", "+ driverResultModel.getResult2());

				driverResultProvider.updateDataByID(driverResultModel.getId(),resultModel);
			}
		}



		return message.toString();
	}

}
