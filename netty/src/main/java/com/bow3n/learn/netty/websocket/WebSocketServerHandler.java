package com.bow3n.learn.netty.websocket;

import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.PromiseCombiner;

/**
 * @author bowen
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private static final ChannelGroup allChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        if (frame instanceof TextWebSocketFrame) {
            PromiseCombiner promiseCombiner = new PromiseCombiner();
            allChannels.stream()
                    .filter(c -> c != ctx.channel())
                    .forEach(c -> {
                        frame.retain();
                        promiseCombiner.add(c.writeAndFlush(frame.duplicate()));
                    });
            Promise aggPromise = ctx.newPromise();
            promiseCombiner.finish(aggPromise);
            aggPromise.addListener((ChannelFutureListener) channelFuture -> {
                if (frame.release()) {
                    channelFuture.channel().close();
                    System.out.println("success");
                } else {
                    System.out.println("error");
                }
            });

        } else {
            // For now, let's ignore binary frames
            throw new UnsupportedOperationException("Invalid websocket frame received");
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        allChannels.add(ctx.channel());
    }
}
