package com.unionfin.concurrent;

import java.util.concurrent.TimeUnit;

public class Interrupted
{
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws InterruptedException
    {
        // sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        // busyThread不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        // 休眠5秒，让sleepThread 和busyThread 从分运行
        Thread.currentThread().sleep(5000);

        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is "
                + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is "
                + sleepThread.isInterrupted());
        // 防止sleepThread 和 busyThread 立刻退出
        Thread.currentThread().sleep(2000);

    }

    static class SleepRunner implements Runnable
    {

        public void run()
        {
            while (true)
            {

                try
                {
                    TimeUnit.SECONDS.sleep(10);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
        }

    }

    static class BusyRunner implements Runnable
    {

        public void run()
        {
            while (true)
            {

            }
        }

    }
}
