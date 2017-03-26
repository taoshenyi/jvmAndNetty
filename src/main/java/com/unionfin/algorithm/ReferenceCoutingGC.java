package com.unionfin.algorithm;

/**
 * 相互引用
 * 
 * @author xiaotao
 * 
 */
public class ReferenceCoutingGC
{
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];


    public static void main(String[] args)
    {
        ReferenceCoutingGC objA = new ReferenceCoutingGC();
        ReferenceCoutingGC objB = new ReferenceCoutingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        System.gc();

    }
}
