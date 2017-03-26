package com.unionfin.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class TimeServerHandler extends ChannelHandlerAdapter
{
    private int counter;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception
    {
        System.out.println("server channelRead..");
        /*
         * ByteBuf buf = (ByteBuf) msg; byte[] req = new
         * byte[buf.readableBytes()]; buf.readBytes(req); String body = new
         * String(req, "UTF-8").substring(0, req.length -
         * System.getProperty("line.separator").length());
         */
        String body = (String) msg;
        System.out.println("The time server receive order : " + body
                + "; the counter is : " + ++counter);
        String currentTime = "Query Time order".equalsIgnoreCase(body) ? new Date(
                System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
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
