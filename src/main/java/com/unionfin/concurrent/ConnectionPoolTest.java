package com.unionfin.concurrent;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest
{
    static ConnectionPool pool = new ConnectionPool(10);
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;


    public static void main(String[] args) throws InterruptedException
    {
        int threadCount = 10;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++)
        {
            Thread thread = new Thread(
                    new ConnectionDriver(count, got, notGot),
                    "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection" + got);
        System.out.println("not got connection" + notGot);
    }

    static class ConnectionDriver implements Runnable
    {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;


        public ConnectionDriver(int count, AtomicInteger got,
                AtomicInteger notGot)
        {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }


        public void run()
        {
            try
            {
                start.await();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            while (count > 0)
            {
                try
                {
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null)
                    {
                        try
                        {
                            connection.createStatement();
                            connection.commit();
                        }
                        finally
                        {
                            pool.releaseConnection(connection);

                            got.incrementAndGet();
                        }

                    }
                    else
                    {
                        notGot.incrementAndGet();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    count--;
                }
            }
        }

    }
}
