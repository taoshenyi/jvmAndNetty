package com.unionfin.algorithm;

public class RuntimeConstantPoolOOM1
{
    public static void main(String[] args)
    {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
    }
}
