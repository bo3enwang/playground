package com.bow3n.learn.netty.jdk;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class PlainOioServer {
    public void server(int port) {
        try {
            final ServerSocket serverSocket = new ServerSocket(port);
            for (; ; ) {
                final Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                new Thread(() -> {
                    System.out.println("rock");
                    OutputStream outputStream;
                    try {
                        outputStream = clientSocket.getOutputStream();
                        outputStream.write("Hi! \r\n".getBytes(Charset.forName("UTF-8")));
                        outputStream.flush();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PlainOioServer().server(4810);
    }
}
