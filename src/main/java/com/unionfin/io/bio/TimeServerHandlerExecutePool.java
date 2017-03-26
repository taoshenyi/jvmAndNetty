package com.unionfin.io.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool
{
    private ExecutorService executor;


    public TimeServerHandlerExecutePool(int maxPoolSize, int querySize)
    {
        executor = new ThreadPoolExecutor(Runtime.getRuntime()
                .availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(querySize));
    }


    public void execute(Runnable task)
    {
        executor.execute(task);
    }
}
