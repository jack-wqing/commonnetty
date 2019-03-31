package com.netty.test.echoserver;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * author liuwenqing
 * createTime 2019-03-03 16:23
 * ChannelHandler 处理客户端代码
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

    private int counter;

    private final String ECHO_REQ = "hi.liuwenqing welcome to netty $_";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int index = 0; index < 100; index++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("this is " + ++counter + "times receive server :[" + body + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
