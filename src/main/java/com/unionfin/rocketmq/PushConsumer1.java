package com.unionfin.rocketmq;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

public class PushConsumer1
{
    public static void main(String[] args)
    {
        final AtomicInteger integer = new AtomicInteger();
        final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                "PushConsumer");
        try
        {
            consumer.setNamesrvAddr("192.168.106.148:9876");
            consumer.subscribe("testTopic", "testTag");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        }
        catch (MQClientException e2)
        {
            e2.printStackTrace();
        }
        for (int i = 0; i < 10; i++)
        {
            new Thread(new Runnable() {

                public void run()
                {

                    try
                    {

                        consumer.registerMessageListener(new MessageListenerConcurrently() {
                            public ConsumeConcurrentlyStatus consumeMessage(
                                    List<MessageExt> msgs,
                                    ConsumeConcurrentlyContext Context)
                            {
                                try
                                {
                                    processMsg(msgs, integer);
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

                            }
                        });
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {}
                }
            }, "Thread******" + i).start();
        }

        try
        {
            Thread.sleep(1000);
            consumer.start();

        }
        catch (MQClientException e1)
        {
            e1.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }


    public static void processMsg(List<MessageExt> msgs, AtomicInteger integer)
    {
        for (MessageExt messageExt : msgs)
        {
            String recvJsonStr = new String();
            try
            {
                recvJsonStr = new String(messageExt.getBody(), "UTF-8");
                System.out.println(Thread.currentThread().getName() + "***"
                        + integer.get() + " Receive New Message: "
                        + recvJsonStr);
                System.out.println(Thread.currentThread().getName() + "***"
                        + integer.get() + " Message ID:: "
                        + messageExt.getMsgId());
                TaskProcReqDto reqDto = JSON.parseObject(recvJsonStr,
                        TaskProcReqDto.class);
                System.out.println(Thread.currentThread().getName() + "***"
                        + integer.get() + "**********oid*************"
                        + reqDto.getBillProcReqOid());
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
    }
}
