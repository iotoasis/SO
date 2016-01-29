package com.pineone.icbms.so.iot.resources.model.repo.driver.result;

import com.pineone.icbms.so.resources.model.IGenericModel;

/**
 * Created by pahnj on 2016-01-09.
 */
public interface IDriverResultModel extends IGenericModel
{
	/**
	 * type: so/resource/driverresult
	 */
	String TYPE = "so/resource/driverresult";

	/**
	 * return send message.<BR/>
	 * 
	 * @return send message
	 */
	String getSendMessage();

	/**
	 * set a send message.<BR/>
	 * 
	 * @param sendMessage
	 *            send message
	 */
	void setSendMessage(String sendMessage);

	/**
	 * return result1.<BR/>
	 * 
	 * @return result1
	 */
	String getResult1();

	/**
	 * set a result1.<BR/>
	 * 
	 * @param result1
	 *            result1
	 */
	void setResult1(String result1);

	/**
	 * return result2.<BR/>
	 * 
	 * @return result2
	 */
	String getResult2();

	/**
	 * set a result2.<BR/>
	 * 
	 * @param result2
	 */
	void setResult2(String result2);

	/**
	 * return commandId
	 * @return commandId
     */
	public String getCommandId();

	/**
	 *set a commandId
	 */
	public void setCommandId(String commandId);

	/**
	 * return value
	 * @return
     */
	public String getValue();

	/**
	 * set a value
	 * @param value
     */
	public void setValue(String value);

	/**
	 * return deviceUrl
	 * @return
     */
	public String getDeviceUrl();

	/**
	 * set a deviceUrl
	 * @param url
     */
	public void setDeviceUrl(String url);


}
