package com.bow3n.learn.netty.download;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.ReferenceCountUtil;

/**
 * @author bowen
 */
public class HttpDownInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast("httpCodec", new HttpClientCodec());
        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                System.out.println("read");
                if (msg instanceof HttpContent) {
                    HttpContent httpContent = (HttpContent) msg;
                    ByteBuf byteBuf = httpContent.content();
                    int size = byteBuf.readableBytes();
                    System.out.println(size);
                }
                ReferenceCountUtil.release(msg);
            }
        });
    }
}
