package com.pineone.icbms.so.server.controller.validation;

import com.pineone.icbms.so.iot.resources.occurrence.DefaultOccurrence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Melvin on 2015. 12. 22..
 * Occurrence Validator<BR/>
 */
public class OccControllerValidation
{

	public void inspectOccController(DefaultOccurrence occurrence)
			throws Exception
	{

		if (occurrence.getDomainList() == null || occurrence.getDomainList().size() == 0)
			throw new NotExistDomainException();

		if (occurrence.getContextId() == null || occurrence.getDomainList().size() == 0
				|| occurrence.getContextId().equals(""))
			throw new NotExistContextIdException();

		if (occurrence.getTime() == null || occurrence.getTime().equals("") || occurrence.getTime().length() == 0)
			throw new NotExistTimeException();

		if (getFutureTime(occurrence.getTime()) == true)
			throw new NotUsefulTimeException();
	}

	public boolean getFutureTime(String occurTime)
	{

		SimpleDateFormat reFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

		Calendar calendar = Calendar.getInstance();
		Date currentTime = calendar.getTime();

		Date occDate = null;
		try
		{
			occDate = reFormat.parse(occurTime);

		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		if (currentTime.getTime() < occDate.getTime())
		{
			return true;
		}
		else
		{
			return false;
		}

	}
}
