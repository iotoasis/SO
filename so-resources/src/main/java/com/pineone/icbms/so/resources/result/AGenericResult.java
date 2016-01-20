package com.pineone.icbms.so.resources.result;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Generic result abstract class.<BR/>
 * Created by uni4love on 2015. 10. 20..
 */
abstract public class AGenericResult implements IGenericResult, Serializable
{
	private static final long serialVersionUID = 3664754380402892058l;

	/**
	 * STATUS_CODE_VARIABLE_PREFIX
	 */
	public static final String STATUS_CODE_VARIABLE_PREFIX = "SC_";

	/**
	 * CODE strings map
	 */
	protected static Map<Integer, String> codeStrings = null;

	static
	{
		codeStrings = new HashMap<Integer, String>();
		Field[] fields = AGenericResult.class.getFields();
		for (Field field : fields)
		{
			try
			{
				if (field.getName().startsWith(STATUS_CODE_VARIABLE_PREFIX))
				{
					codeStrings.put(field.getInt(AGenericResult.class),
							field.getName().replace(STATUS_CODE_VARIABLE_PREFIX,
									""));
				}
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * return text for status code.<BR/>
	 *
	 * @param code
	 *            status code
	 * @return text for code
	 */
	public static String getCodeString(int code)
	{
		return codeStrings.get(code);
	}
}
