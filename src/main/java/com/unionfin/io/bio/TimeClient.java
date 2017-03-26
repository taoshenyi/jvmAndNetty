package com.unionfin.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient
{
    public static void main(String[] args)
    {
        int port = 8080;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try
        {
            socket = new Socket("localhost", port);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Query Time order");
            System.out.println("Send order 2 server succeed");
            String resp = in.readLine();
            System.out.println("Now is : " + resp);
        }
        catch (Exception e)
        {
            // finally
        }
        finally
        {
            if (out != null)
            {
                out.close();
                out = null;
            }
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                in = null;
            }
            if (socket != null)
            {
                try
                {
                    socket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
