#### 一、概念

 1、NIO2.0引入了新的异步通道的概念，并且停供了异步文件通道和异步套接字通道的实现。



2、NIO2.0的异步套接字通道是真正的异步非阻塞I/O。他不需要通过多路复用器（Selector）对注册的通道进行轮询套作即可实现异步读写，从而简化了NIO的编程模型。