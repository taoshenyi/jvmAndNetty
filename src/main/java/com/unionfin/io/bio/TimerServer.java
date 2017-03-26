package com.unionfin.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimerServer
{
    public static void main(String[] args) throws IOException
    {
        int port = 8080;
        ServerSocket server = null;

        try
        {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port: " + port);
            Socket socket = null;
            TimeServerHandlerExecutePool executor = new TimeServerHandlerExecutePool(
                    50, 10000);
            while (true)
            {
                socket = server.accept();
                executor.execute(new TimeServerHandler(socket));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (server != null)
            {
                System.out.println("The time Server close");
                server.close();
                server = null;
            }
        }
    }
}
