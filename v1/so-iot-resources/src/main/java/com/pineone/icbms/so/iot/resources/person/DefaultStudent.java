package com.pineone.icbms.so.iot.resources.person;

import com.pineone.icbms.so.resources.vo.person.DefaultPerson;

/**
 * Student default class.<BR/>
 * Created by Melvin on 2016. 1. 10..
 */
public class DefaultStudent extends DefaultPerson implements IStudent
{
    /**
     * student id
     */
	String studentId;

    /**
     * school name
     */
	String schoolName;

    /**
     * department name
     */
	String departmentName;

    /**
     * uri
     */
	String uri;

	@Override
	public String getStudentId()
	{
		return studentId;
	}

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSchoolName()
	{
		return schoolName;
	}

	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}

	public String getDepartmentName()
	{
		return departmentName;
	}

	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	@Override
	public String getUri()
	{
		return uri;
	}

	public void setUri(String uri)
	{
		this.uri = uri;
	}
}
