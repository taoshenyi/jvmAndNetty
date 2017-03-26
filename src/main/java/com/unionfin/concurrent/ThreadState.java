package com.unionfin.concurrent;

import java.util.concurrent.TimeUnit;

public class ThreadState
{
    public static void main(String[] args)
    {
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }

    static class TimeWaiting implements Runnable
    {

        public void run()
        {

            try
            {
                TimeUnit.SECONDS.sleep(30);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

    static class Waiting implements Runnable
    {

        public void run()
        {
            while (true)
            {
                synchronized (Waiting.class)
                {
                    try
                    {
                        Waiting.class.wait();
                    }
                    catch (InterruptedException e)

                    {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    static class Blocked implements Runnable
    {

        public void run()
        {
            synchronized (Blocked.class)
            {
                while (true)
                {
                    try
                    {
                        TimeUnit.SECONDS.sleep(100);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
