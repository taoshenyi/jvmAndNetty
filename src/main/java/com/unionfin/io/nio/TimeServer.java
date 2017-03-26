package com.unionfin.io.nio;

public class TimeServer
{
    public static void main(String[] args)
    {
        int port = 8080;
        new Thread(new MultiplexerTimeServer(port)).start();
        ;
    }
}
