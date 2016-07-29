package com.pineone.icbms.so.resources.vo.person;

/**
 * Person default class.<BR/>
 * Created by Melvin on 2016. 1. 10..
 */
public class DefaultPerson extends AGenericPerson
{
	public void setPhone(String phone)
	{
		this.phoneNum = phone;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
}
