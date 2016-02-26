package com.pineone.icbms.so.iot.resources.model.repo.driver.result;

import com.pineone.icbms.so.resources.model.AGenericModel;

/**
 * Device Result model class for repository.<BR/>
 * Created by pahnj on 2016-01-09.
 */
public class DriverResultModel extends AGenericModel
		implements IDriverResultModel
{
	/**
	 * SI send Message (Json data)
	 */
	String sendMessage;

	/**
	 * Transmission confirmation results
	 */
	String result1;

	/**
	 * Control check results
	 */
	String result2;

	/**
	 *  Command Id
     */
	String commandId;

	/**
	 * control value
	 */
	String value;

	/**
	 *  control deviceUrl
	 */
	String deviceUrl;

	@Override
	public String getType()
	{
		return TYPE;
	}

	@Override
	public String getSendMessage()
	{
		return sendMessage;
	}

	@Override
	public void setSendMessage(String sendMessage)
	{
		this.sendMessage = sendMessage;
	}

	@Override
	public String getResult1()
	{
		return result1;
	}

	@Override
	public void setResult1(String result1)
	{
		this.result1 = result1;
	}

	@Override
	public String getResult2()
	{
		return result2;
	}

	@Override
	public void setResult2(String result2)
	{
		this.result2 = result2;
	}

	@Override
	public String getCommandId() {
		return commandId;
	}

	@Override
	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getDeviceUrl() {
		return deviceUrl;
	}

	@Override
	public void setDeviceUrl(String deviceUrl) {
		this.deviceUrl = deviceUrl;
	}


}
