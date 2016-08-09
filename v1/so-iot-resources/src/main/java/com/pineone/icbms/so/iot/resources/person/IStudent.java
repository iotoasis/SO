package com.pineone.icbms.so.iot.resources.person;

import com.pineone.icbms.so.resources.vo.person.IGenericPerson;

/**
 * Student interface.<BR/>
 * Created by Melvin on 2016. 1. 10..
 */
public interface IStudent extends IGenericPerson
{
	/**
	 * return student id.<BR/>
	 * 
	 * @return student id
	 */
	String getStudentId();

	/**
	 * return school name.<BR/>
	 * 
	 * @return school name
	 */
	String getSchoolName();

	/**
	 * return department name.<BR/>
	 * 
	 * @return department name
	 */
	String getDepartmentName();

	/**
	 * return uri.<BR/>
	 * @return uri
     */
    String getUri();
}
