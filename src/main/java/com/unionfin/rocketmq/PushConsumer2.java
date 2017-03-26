package com.unionfin.rocketmq;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

public class PushConsumer2
{
    public static void main(String[] args)
    {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                "PushConsumer");
        consumer.setNamesrvAddr("192.168.106.148:9876");
        try
        {

            consumer.subscribe("PushTopic1", "push");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> list,
                        ConsumeConcurrentlyContext Context)
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
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

                }
            });
            consumer.start();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // consumer.shutdown();
        }
    }

}
