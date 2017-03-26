package com.unionfin.beanFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test
{

    public static void main(String[] args)
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "spring/applicationContext.xml");

        SpringContextHelper helper = new SpringContextHelper();
        helper.setApplicationContext(applicationContext);
        IBillTradeCtrlTaskProcFactory factory = (IBillTradeCtrlTaskProcFactory) SpringContextHelper
                .getBean("billTradeCtrlTaskProcFactory");
        Car car = factory.getCar("0001");
        car.print();

    }
}
