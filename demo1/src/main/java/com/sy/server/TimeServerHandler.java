package com.sy.server;



import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Author: shenyao
 * @Date: Created by 2020/11/4 22:23
 * @description: 继承自ChannelHandlerAdapater，他用于对网络时间进行读写操作，
 * 通常我们只需要关注channelRead和exceptionCaught方法。
 */
public class TimeServerHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        //转为Netty的ByteBuf对象。比java自带的功能更强大
        ByteBuf buf  =(ByteBuf)msg;
        /**
         * buf.readableBytes() 获取可读的字节数
         * 随后根据可读的字数创建byte数组。
         * 在通过方法将缓冲区的字节数组复制到新的数组。
         */
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("打印值为："+body);
        String currentTime =  "QUERY".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        channelHandlerContext.write(resp);
    }

    /**
     * 发生意外，关闭释放资源
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    /**
     * 这里的作用是将消息发列队中的消息写入socketChannel中发送给对方。
     * 从性能角度，为了防止频繁唤醒Selector进行消息发送，Netty的write方法不直接写入SocketChannel中
     * 是先发送到缓冲区数组。在通过flush方法将缓冲区中的消息全部写到SocketChannel。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
