package com.unionfin.rocketmq;

import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.common.message.MessageExt;

public class TransactionCheckListenerImpl implements TransactionCheckListener
{
    private AtomicInteger transactionIndex = new AtomicInteger(3);


    public LocalTransactionState checkLocalTransactionState(MessageExt msg)
    {
        System.out.println("Server checking TrMsg" + msg.toString());
        int value = transactionIndex.getAndIncrement();
        if ((value % 6) == 0)
        {
            throw new RuntimeException("Could not find db");
        }
        else if ((value % 5) == 0)
        {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        else if ((value % 4) == 0)
        {
            return LocalTransactionState.COMMIT_MESSAGE;
        }

        return LocalTransactionState.UNKNOW;
    }
}
