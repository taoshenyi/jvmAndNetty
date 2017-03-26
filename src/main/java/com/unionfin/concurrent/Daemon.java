package com.unionfin.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 设置守护线程
 * 
 * @author xiaotao
 * 
 */
public class Daemon
{
    public static void main(String[] args)
    {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.start();
    }

    static class DaemonRunner implements Runnable
    {

        public void run()
        {
            try
            {
                TimeUnit.SECONDS.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            finally
            {
                System.out.println("DaemonThread finally run");
            }
        }

    }
}
