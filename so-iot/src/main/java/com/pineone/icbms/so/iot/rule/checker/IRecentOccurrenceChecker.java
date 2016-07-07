package com.pineone.icbms.so.iot.rule.checker;

import com.pineone.icbms.so.iot.resources.occurrence.DefaultOccurrence;

/**
 * Created by existmaster on 2016. 1. 26..
 */
public interface IRecentOccurrenceChecker {

    Boolean recentOccurrenceCheck(DefaultOccurrence defaultOccurrence);

}
