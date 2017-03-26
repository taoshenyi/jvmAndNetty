package com.unionfin.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 多路复用类
 * 
 * @author xiaotao
 * 
 */
public class MultiplexerTimeServer implements Runnable
{
    /** 多路复用器 **/
    private Selector selector;

    private ServerSocketChannel servChannel;

    private volatile boolean stop;


    /**
     * 初始化多路复用器，绑定监听端口
     * 
     * @param port
     */
    public MultiplexerTimeServer(int port)
    {
        try
        {
            selector = Selector.open();
            servChannel = ServerSocketChannel.open();
            servChannel.configureBlocking(false);
            servChannel.socket().bind(new InetSocketAddress(port), 1024);
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port:" + port);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public void stop()
    {
        this.stop = true;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run()
    {
        while (!stop)
        {
            try
            {
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext())
                {
                    key = it.next();
                    it.remove();
                    try
                    {
                        handleInput(key);
                    }
                    catch (Exception e)
                    {
                        if (key != null)
                        {
                            key.cancel();
                            if (key.channel() != null)
                            {
                                key.channel().close();
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (selector != null)
        {
            try
            {
                selector.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    private void handleInput(SelectionKey key) throws IOException
    {
        if (key.isValid())
        {
            // 处理新接入的请求消息
            if (key.isAcceptable())
            {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable())
            {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0)
                {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("Time server receive order :" + body);
                    String currentTime = "Query Time order"
                            .equalsIgnoreCase(body) ? new Date(
                            System.currentTimeMillis()).toString()
                            : "bad order";
                    doWrite(sc, currentTime);
                }
            }
        }
    }


    private void doWrite(SocketChannel channel, String response)
            throws IOException
    {
        if (response != null && response.trim().length() > 0)
        {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }

}
