package com.unionfin.algorithm;

public class KMPTest
{
    public static void main(String[] args)
    {
        KMPTest test = new KMPTest();
        test.getNext("abcde");
    }


    // /////KMP模式匹配算法/////////
    public int[] getNext(String T)
    {
        // 创建一个数组，长度为字符串T的长度
        int[] next = new int[T.length()];
        int i = 0;
        int j = -1;
        next[i] = j;
        while (i < T.length() - 1)
        {
            if (j == -1 || T.charAt(i) == T.charAt(j))
            {
                i++;
                j++;
                next[i] = j;
            }
            else
            {
                j = next[j];// 若字符不相等，则j值进行回溯。

            }
        }
        for (int k = 0; k < next.length; k++)
        {
            System.out.println(next[k]);
        }
        System.out.println();
        return next;
    }


    public int indexKMP(String s, String t, int pos)
    {
        int i = pos;
        int j = 0;
        int[] next = getNext(t);
        while (i < s.length() && j < t.length())
        {
            if (j == -1 || s.charAt(i) == t.charAt(j))
            {
                i++;
                j++;
            }
            else
            {// 重新开始匹配
                j = next[j];// j退回到合适的位置，i值不变
            }
        }
        if (j >= t.length())
        {
            return i - t.length();
        }
        else
        {
            return 0;
        }
    }

}
