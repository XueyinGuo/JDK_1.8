package com.szu.nio.client;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/28 16:38
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class L01_BIO_Client {
    public static void main(String[] args) {
        int port = 9091;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new PrintWriter(socket.getOutputStream(), true));
            out.println("Query Time Order");
            System.out.println("Send order to server success");
            String resp = in.readLine();
            System.out.println("Now is : "+ resp);
        } catch (IOException e) {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();

                e.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }
}
