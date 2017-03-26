package com.unionfin.io.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class FixedLengthEchoServerHandler extends ChannelHandlerAdapter
{
    private int counter = 0;;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception
    {
        System.out.println("Receive client :[" + msg + "]");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception
    {
        System.out.println("server exceptionCaught..");
        ctx.close();
    }

}
