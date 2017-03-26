package com.unionfin.rocketmq;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

public class Producer
{

    public static void main(String[] args) throws InterruptedException,
            MQClientException
    {
        final DefaultMQProducer producer = new DefaultMQProducer("Producer");
        final AtomicInteger integer = new AtomicInteger();
        producer.setNamesrvAddr("192.168.106.148:9876");
        producer.start();
        producer.setSendMsgTimeout(100000);
        for (int i = 0; i < 1; i++)
        {
            Thread thread = new Thread(new Runnable() {
                public void run()
                {
                    try
                    {
                        for (int k = 0; k < 50; k++)
                        {
                            TaskProcReqDto reqDto = new TaskProcReqDto();
                            reqDto.setBillProcReqOid(new BigDecimal(integer
                                    .getAndIncrement()));
                            String sendJsonString = JSON.toJSONString(reqDto);
                            System.out
                                    .println("billProcReqTaskProc ReqDto json str = "
                                            + sendJsonString);
                            Message msg = new Message("testTopic", "testTag",
                                    sendJsonString.getBytes("UTF-8"));
                            SendResult result = producer.send(msg);
                            System.out
                                    .println("billProcReqTaskProc via Mq and sendResult = "
                                            + result);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }

    }
}
