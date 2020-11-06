package com.sy.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Author: shenyao
 * @Date: Created by 2020/11/5 20:41
 * @description: 时间服务器的客户端
 */
public class TimeClient {

    public void connect(int port,String host) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            //客户端辅助启动类，
            Bootstrap b = new Bootstrap();
            // 与服务端不同的是，他的Channel需要设置为Nio
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        // 这里的作用是当创建NioSocketChannel成功后，进行初始化时，将handler设置到pipeline。
                        // 用于处理 网络I/O事件
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            //发起异步连接操作
            ChannelFuture f = b.connect(host, port).sync();
            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        }finally {
            // 优雅退出
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        int port=8080;
        new TimeClient().connect(port,"127.0.0.1");
    }



}
