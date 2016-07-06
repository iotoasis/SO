package com.pineone.icbms.so.iot.provider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public abstract class AGeneralProvider implements IProvider {
	
	protected final String id_str = "id";
	protected final String name_str = "name";
	
	
	public AGeneralProvider(){
		
	}
	
	/**
	 * Provide now Date Time :: OnTology Format
	 * @return now DateTime (format ex : 20160111T170523) 
	 */
	public String getNowDateOnTology(){
		Calendar calendar = Calendar.getInstance();
		String nowDate = new SimpleDateFormat("yyyyMMdd'T'HHmmss").format(calendar.getTime());
		return nowDate;
	}
	
	/**
	 * Provide now Date 
	 * @return now Date  
	 */
	public Date getNowDate(){
		Date nowDate = new Date();
		return nowDate;
	}
	
	/**
	 * set Model Object Property(createdDate, modifiedDate)
	 * @param object Model Object
	 * @param dateMethodName setCreateDate or setEditeDate
	 */
	public <D> void setModelDate(D data, String dateMethodName){
		try {
			//Method method = data.getClass().getMethod(dateMethodName, new String().getClass()); by method getNowDateOnTology()
			Method method = data.getClass().getMethod(dateMethodName, new Date().getClass());
			try {
				method.invoke(data, getNowDate());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

}
