package com.unionfin.rocketmq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;

public class TransactionProducer
{
    public static void main(String[] args) throws MQClientException,
            InterruptedException
    {
        TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer("producer");
        // 事物回查最小并发数
        producer.setCheckThreadPoolMinSize(2);
        producer.setCheckThreadPoolMaxSize(2);
        // 队列数
        producer.setCheckRequestHoldMax(2000);
        producer.setTransactionCheckListener(transactionCheckListener);
        producer.setNamesrvAddr("192.168.106.148:9876");
        producer.start();
        for (int i = 0; i < 1; i++)
        {
            try
            {
                TransactionExecuterImpl tranExecuter = new TransactionExecuterImpl();
                Message msg = new Message("testTopic", "testTag",
                        ("Hello RocketMQ " + i).getBytes());
                SendResult sendResult = producer.sendMessageInTransaction(msg,
                        tranExecuter, null);
                System.out.println(sendResult);

                Thread.sleep(10);
            }
            catch (MQClientException e)
            {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 100000; i++)
        {
            Thread.sleep(1000);
        }

        producer.shutdown();
    }
}
