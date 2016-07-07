package com.pineone.icbms.so.iot.resources.value;

/**
 * Created by Melvin on 2016. 1. 10..
 */
abstract class AGenericValue implements IGenericValue
{
    /**
     * Minimum Value
     */
	double minValue;

    /**
     * Maximum Value
     */
	double maxValue;

	@Override
	public double getMinValue()
	{
		return minValue;
	}

	@Override
	public double getMaxValue()
	{
		return maxValue;
	}

}