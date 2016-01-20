package com.pineone.icbms.so.iot.resources.model.repo.driver.result;

import com.pineone.icbms.so.resources.model.AGenericModel;

/**
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
}
