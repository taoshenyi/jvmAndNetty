package com.unionfin.beanFactory;

import org.springframework.stereotype.Service;

@Service("car")
public class Car
{
    private Brand brand;


    public Brand getBrand()
    {
        return brand;
    }


    public void setBrand(Brand brand)
    {
        this.brand = brand;
    }


    public void print()
    {
        System.out.println("this is a car");
    }


    public void init()
    {
        System.out.println("init");
    }


    public void destory()
    {
        System.out.println("destroy");
    }
}
