package com.bow3n.learn.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.ssl.OpenSslClientContext;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        SslContext context = new OpenSslClientContext();
        SSLEngine engine = context.newEngine(ch.alloc());

        ChannelPipeline pipeline = ch.pipeline();
        ch.pipeline().addFirst("ssl",
                new SslHandler(engine, true));
//            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new WebSocketServerCompressionHandler());
        pipeline.addLast(new WebSocketServerProtocolHandler("/", null, true));
        pipeline.addLast(new WebSocketServerHandler());
//        pipeline.addLast(new TestInboundHandler());

//        ch.eventLoop().schedule()
    }
}
