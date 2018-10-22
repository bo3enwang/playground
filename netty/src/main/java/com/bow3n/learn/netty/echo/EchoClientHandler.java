package com.bow3n.learn.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


/**
 * @author bowen
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    /*
     * 首先，你重写了 channelActive()方法，其将在一个连接建立时被调用。这确保了数据
     * 将会被尽可能快地写入服务器，其在这个场景下是一个编码了字符串"Netty rocks!"的字节
     * 缓冲区。
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    /*
     * 第一次使用一个持有 3 字节的 ByteBuf（Netty 的字节容器），第二次使用一个
     * 持有 2 字节的 ByteBuf。作为一个面向流的协议，TCP 保证了字节数组将会按照服务器发送它
     * 们的顺序被接收。
     *
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
