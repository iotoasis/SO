package com.pineone.icbms.so.server.controller;

import com.pineone.icbms.so.iot.provider.DriverResultProvider;
import com.pineone.icbms.so.iot.resources.message.ResultMessage;
import com.pineone.icbms.so.iot.resources.model.repo.driver.result.DriverResultModel;
import com.pineone.icbms.so.iot.util.service.DataConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeviceMonitor
{
	private final Logger log = LoggerFactory.getLogger(DeviceMonitor.class);

	DriverResultProvider	driverResultProvider	= new DriverResultProvider();
	DriverResultModel		driverResultModel		= null;

	/**
	 * 응답 결과를 받아서 결과 여부를 판단함.<BR/>
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

		log.info("insert device moniter controller");
		log.info("ResultMessage _commandId = " + message.get_commandId()
				+ " | resultCode = " + message.get_resultCode() + " | result = "
				+ message.get_result());

		System.out.println("[[DeviceMoniter message]] = " + message.toString());

		/**
		 * DB에 ID에 대한 데이터가 있으면 DB에 result2값 저장
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
			driverResultModel.setResult2(message.get_resultCode());
			driverResultProvider.updateDataByID(driverResultModel.getId(),
					driverResultModel);
		} else {
			if(driverResultModel != null)
			{
				log.info("fail resultData and the response has been properly.");
				driverResultModel.setResult2(message.get_resultCode());
				driverResultProvider.updateDataByID(driverResultModel.getId(),driverResultModel);
			}
		}

		return message.toString();
	}

}
