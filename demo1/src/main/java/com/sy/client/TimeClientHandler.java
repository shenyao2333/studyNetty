package com.sy.client;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: shenyao
 * @Date: Created by 2020/11/5 20:45
 * @description: 客户端处理器
 */

public class TimeClientHandler extends SimpleChannelInboundHandler {

    private ByteBuf firstMessage;

    private byte[] req;

    private int counter;

    public TimeClientHandler(){
        req = ("QUERY"+System.getProperty("line.separator")).getBytes();
        firstMessage= Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }


    /**
     * 当收到应答的消息时，channelRead方法被调用。
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {

        String buf = (String)msg;
        System.out.println("NOW is: "+ buf  + "  the count is:" + ++counter);

    }


    /**
     * 当客户端和服务端TCP链路建立成功后，Netyy的NIO线程会调用channelActive方法
     * 发送时间的指令给服务端，调用ChannelHandlerContext的writeAndFlush方法将请求消息发送给服务端
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }

    }

    /**
     * 当发生异常时，释放资源
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getLocalizedMessage());
        System.out.println("错误："+cause.getMessage());
        ctx.close();
    }
}
