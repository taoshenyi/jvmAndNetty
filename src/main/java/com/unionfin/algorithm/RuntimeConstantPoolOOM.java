package com.unionfin.algorithm;

import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantPoolOOM
{
    public static void main(String[] args)
    {
        // 使用list 保持着常量池的引用，避免 FULL GC回收常量池行为
        List<String> list = new ArrayList<String>();

        int i = 0;
        while (true)
        {
            list.add(String.valueOf(i++).intern());
        }
    }
}
