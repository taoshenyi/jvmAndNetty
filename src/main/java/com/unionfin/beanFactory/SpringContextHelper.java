package com.unionfin.beanFactory;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHelper implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;


    public void setApplicationContext(ApplicationContext applicationContext)
    {
        SpringContextHelper.applicationContext = applicationContext;
    }


    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }


    public static Object getBean(String name) throws BeansException
    {
        return applicationContext.getBean(name);
    }


    public static <T> T getBeanByClazz(Class<T> classname)
    {
        return applicationContext.getBean(classname);
    }


    public static <T> Map<String, T> getBeansByClazz(Class<T> classname)
    {
        return applicationContext.getBeansOfType(classname);
    }
}
