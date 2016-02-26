package com.pineone.icbms.so.iot.resources.driver.result;

/**
 * Default Driver Result Class.<BR/>
 * Created by pahnj on 2016-01-09.
 */
public class DefaultDriverResult extends AGenericDriverResult
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

	public DefaultDriverResult()
	{
	}

	public DefaultDriverResult(String sendMessage, String result1,
			String result2)
	{
		this.sendMessage = sendMessage;
		this.result1 = result1;
		this.result2 = result2;
	}

	public String getSendMessage()
	{
		return sendMessage;
	}

	public void setSendMessage(String sendMessage)
	{
		this.sendMessage = sendMessage;
	}

	public String getResult1()
	{
		return result1;
	}

	public void setResult1(String result1)
	{
		this.result1 = result1;
	}

	public String getResult2()
	{
		return result2;
	}

	public void setResult2(String result2)
	{
		this.result2 = result2;
	}

	@Override
	public String toString()
	{
		return "DefaultDriverResult{" + "sendMessage='" + sendMessage + '\''
				+ ", result1='" + result1 + '\'' + ", result2='" + result2
				+ '\'' + '}';
	}
}
