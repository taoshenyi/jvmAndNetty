package com.unionfin.io.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoClientHandler extends ChannelHandlerAdapter
{
    private static final Logger logger = LoggerFactory
            .getLogger(EchoClientHandler.class);

    private int counter;
    static final String ECHO_REQ = "Hi,赵小涛.Welcome to Netty.$_";


    public EchoClientHandler()
    {

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.println("client channelActive..");
        for (int i = 0; i < 100; i++)
        {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception
    {
        System.out.println("client channelRead..");
        System.out.println("This is  " + ++counter + " times receive server :["
                + msg + "]");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception
    {
        System.out.println("client exceptionCaught..");
        logger.warn("Unexpected exception from downstream : "
                + cause.getMessage());
        ctx.close();
    }

}
