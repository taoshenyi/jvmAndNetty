package com.unionfin.concurrent;

public class RecorderExample
{
    int a = 0;
    boolean flag = false;


    public synchronized void writer()
    {
        flag = true;
        a = 1;
    }


    public synchronized void reader()
    {
        if (flag)
        {
            int i = a * a;
            System.out.println("**********************" + i);
        }
    }


    public static void main(String[] args)
    {
        final RecorderExample example = new RecorderExample();
        Thread thread1 = new Thread(new Runnable() {

            public void run()
            {
                example.writer();
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {

            public void run()
            {
                example.reader();
            }
        });
        thread2.start();
    }
}
