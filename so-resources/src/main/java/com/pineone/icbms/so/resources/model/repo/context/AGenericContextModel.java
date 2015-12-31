package com.pineone.icbms.so.resources.model.repo.context;

import java.util.List;
import java.util.Map;

import com.pineone.icbms.so.resources.context.IGenericContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * generic context model abstract class.<BR/>
 * Created by uni4love on 2015. 05. 30..
 */
abstract public class AGenericContextModel<V> implements IGenericContextModel<String, V>
{
    /**
	 * logger
	 */
	private Logger log = LoggerFactory.getLogger(AGenericContextModel.class);

	/**
	 * id
	 */
	protected long id;

	/**
	 * name
	 */
	protected String name;

	/**
	 * context list
	 */
	protected List<IGenericContext> contextList;

	/**
	 * constructor
	 */
	public AGenericContextModel()
	{
	}

	@Override
	public Long getId()
	{
		return id;
	}

	@Override
	public String getName()
	{
		return name;
	}

	/**
	 * return context list.<BR/>
	 * @return context list
	 */
	public List<IGenericContext> getContextList()
	{
		return contextList;
	}
}
