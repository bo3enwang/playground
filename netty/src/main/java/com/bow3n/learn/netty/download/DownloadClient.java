package com.bow3n.learn.netty.download;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author bowen
 */
public class DownloadClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new HttpDownInitializer());
        ChannelFuture future = bootstrap
                .connect("static.sdg-china.com/jijiamobile/pic/ff14/160321linkpage/bt_lodestone_on.png", 80);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("success");
                } else {
                    System.out.println("fucked");
                    future.cause().printStackTrace();
                }
            }
        });
    }
}
