package com.pineone.icbms.so.resources.vo.person;

/**
 * Generic person interface.<BR/>
 * Created by Melvin on 2016. 1. 10..
 */
public interface IGenericPerson extends IPerson
{
	/**
	 * Return Age
	 * 
	 * @return age
	 */
	int getAge();

	/**
	 * Return Sex
	 * 
	 * @return sex
	 */
	String getSex();

	/**
	 * Return E-mail
	 * 
	 * @return email
	 */
	String getEmail();

	/**
	 * Return Address
	 * 
	 * @return address
	 */
	String getAddress();

	/**
	 * return phone number
	 * 
	 * @return phone number
	 */
	String getPhoneNum();

}
