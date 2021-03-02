package com.szu.nio.server;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/28 16:24
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class L01_BIO_Server {
    public static void main(String[] args) throws IOException {
        int port = 9091;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("server start in " + port);
            Socket socket = null;
            while (true) {

                socket = serverSocket.accept();
                new Thread(new TimeServerHandle(socket)).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                System.out.println("The Time Server Close");
                serverSocket.close();
            }
        }
    }

}

class TimeServerHandle implements Runnable {

    private Socket socket;

    public TimeServerHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) break;
                System.out.println("Time Server Receive Order : " + body);
                currentTime = "Query Time Order".equalsIgnoreCase(body) ?
                        new Date(System.currentTimeMillis()).toString() : "Bad Order";
                System.out.println(currentTime);
                out.println(currentTime);
            }
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
