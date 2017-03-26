package com.unionfin.rocketmq;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.PullResult;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;

/**
 * 拉模式消费
 * 
 * @author xiaotao
 * 
 */
public class PullConsumer1
{
    // Java缓存
    private static final Map<MessageQueue, Long> offseTable = new HashMap<MessageQueue, Long>();


    public static void main(String[] args)
    {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(
                "PullConsumer");
        consumer.setNamesrvAddr("192.168.106.148:9876");
        try
        {
            consumer.start();
            Set<MessageQueue> mqs = consumer
                    .fetchSubscribeMessageQueues("testTopic");
            for (MessageQueue mq : mqs)
            {
                System.out.println("Consumer from the queue: " + mq);

                PullResult pullResult = consumer.pullBlockIfNotFound(mq,
                        "testTag", getMessageQueueOffset(mq), 32);
                List<MessageExt> list = pullResult.getMsgFoundList();
                if (list.size() > 0)
                {
                    MessageExt msg = list.get(0);
                    String recvStr = null;
                    try
                    {
                        recvStr = new String(msg.getBody(), "UTF-8");

                    }
                    catch (UnsupportedEncodingException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println(recvStr);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            consumer.shutdown();
        }
    }


    private static long getMessageQueueOffset(MessageQueue mq)
    {
        Long offset = offseTable.get(mq);
        if (offset != null)
        {
            System.out.println(offset);
            return offset;
        }
        return 0;
    }
}
