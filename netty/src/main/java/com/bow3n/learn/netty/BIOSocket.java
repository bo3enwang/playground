package com.bow3n.learn.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelPipeline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOSocket {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4810);
        Socket clientSocket = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String request, reponse;
        while ((request = in.readLine()) != null) {
            if ("Done".equals(request)) {
                break;
            }
            reponse = request + " receive";
            out.println(reponse);
        }
    }
}
