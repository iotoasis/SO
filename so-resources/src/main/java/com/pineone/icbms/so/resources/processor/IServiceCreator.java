package com.pineone.icbms.so.resources.processor;

/**
 * Created by Melvin on 2015. 11. 13..
 * Receive occ form ContextProviderModule
 * Make DefaultTask for sending to ServiceEngineModule
 */


public interface IServiceCreator {

    void executeServiceController(String a);
}
