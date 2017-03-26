package com.unionfin.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoServerHandler extends ChannelHandlerAdapter
{
    private int counter = 0;;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception
    {
        System.out.println("server channelRead..");
        String body = (String) msg;
        System.out.println("This is " + ++counter + "times receive client :["
                + body + "]");
        body += "$_";
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(echo);
    }


    /*
     * @Override public void channelReadComplete(ChannelHandlerContext ctx)
     * throws Exception { System.out.println("server channelReadComplete..");
     * ctx.flush(); }
     */

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception
    {
        System.out.println("server exceptionCaught..");
        ctx.close();
    }

}
