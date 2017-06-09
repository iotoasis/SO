package com.pineone.icbms.so.devicecontrol.util;

import com.pineone.icbms.so.devicecontrol.model.virtualdevice.driver.IGenericDeviceDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * load a device driver.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 15..
 */
public class DeviceDriverLoader {
    /**
     * load device driver class.<BR/>
     *
     * @param path            class path or jar file path
     * @param driverClassName Class name
     */
    public IGenericDeviceDriver loadDeviceDriver(String path, String driverClassName) {
        Class<IGenericDeviceDriver> cls = (Class<IGenericDeviceDriver>) loadClass(path, driverClassName);
        IGenericDeviceDriver driver = null;
        try {
            driver = cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return driver;
    }

    /**
     * load a class.<BR/>
     *
     * @param path      class path
     * @param className class name
     * @return Class
     */
    public Class<?> loadClass(String path, String className) {
        Class<?> cls = null;
        try {
            URL[] urls = {new java.io.File(path).toURL()};
            URLClassLoader classLoader = new URLClassLoader(urls);
            cls = classLoader.loadClass(className);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cls;
    }
}
