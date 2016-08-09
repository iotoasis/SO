package com.pineone.icbms.so.resources.vo.person;

import com.pineone.icbms.so.resources.domain.AGenericDomain;

/**
 * Generic person abstract class.<BR/>
 * Created by Melvin on 2016. 1. 10..
 */
abstract public class AGenericPerson extends AGenericDomain
		implements IGenericPerson
{
	/**
	 * age
	 */
	protected int age;

	/**
	 * sex
	 */
	protected String sex;

	/**
	 * email
	 */
	protected String email;

	/**
	 * address
	 */
	protected String address;

	/**
	 * PhoneNumber
	 */
	protected String phoneNum;

	@Override
	public int getAge()
	{
		return age;
	}

	@Override
	public String getSex()
	{
		return sex;
	}

	@Override
	public String getEmail()
	{
		return email;
	}

	@Override
	public String getAddress()
	{
		return address;
	}

	@Override
	public String getPhoneNum()
	{
		return phoneNum;
	}
}
