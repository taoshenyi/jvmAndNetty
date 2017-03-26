package com.unionfin.algorithm;

/**
 * 朴素模式匹配算法
 * 
 * @author xiaotao
 * 
 */
public class PureTest
{
    // 朴素模式匹配算法
    public int index(String s, String t, int pos)
    {
        int i = pos; // 主串中第几个位置开始比较
        int j = 0;// 模式串中第一个位置
        while (i < s.length() && j < t.length())
        {
            if (s.charAt(i) == t.charAt(j))
            {
                i++;
                System.out.println("&&&&&&&&&&&&&" + i);
                j++;
                System.out.println("&&&&&&&&&&&&&" + j);
            }
            else
            {
                i = i - j + 1;// 主串中的下一个位置
                System.out.println(i);
                j = 0;// 继续第一个位置
            }
        }
        if (j >= t.length())
        {
            System.out.println("******************" + i);
            System.out.println("**********************" + t.length());
            return i - t.length();
        }
        else
        {
            return -1;
        }

    }


    public static void main(String[] args)
    {
        PureTest test = new PureTest();
        System.out.println(test.index("goodgoogle", "google", 2));
    }
}
