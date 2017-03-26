package com.unionfin.beanFactory;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service("billTradeCtrlTaskProcFactory")
public class BillTradeCtrlTaskProcFactory implements
        IBillTradeCtrlTaskProcFactory
{
    private static Map<String, Car> taskProcMap;


    public Car getCar(String taskTypeCode)
    {
        return taskProcMap.get(taskTypeCode);
    }


    public static Map<String, Car> getTaskProcMap()
    {
        return taskProcMap;
    }


    public static void setTaskProcMap(Map<String, Car> taskProcMap)
    {
        BillTradeCtrlTaskProcFactory.taskProcMap = taskProcMap;
    }

}
