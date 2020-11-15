## ByteBuf和相关辅助类

​	这章主要是NettyNIO相关的主要接口和模块的API功能，希望能够更加熟练地掌握和应用这些类库，方便日后的维护、扩展和定制，更能够起到触类旁通的作用。



### 一、ByteBuffer功能说明

​	当我进行数据传输的时候，往往需要使用到缓冲区，常用的缓冲区就是JDKNIO类库提供的java.nio.Buffer。实际上，7种基础类型（Boolean除外）都有自己的缓冲区实现。对于NIO编程而言，我们主要使用的是ByteBuffer。从功能角度而言，ByteBuffer完全可以满足NIO编程的需要，但是由于NIO编程的复杂性，ByteBuffer也有其局限性，如：

- 1、ByteBuffer长度固定，一旦分配完成，它的容量不能动态扩展和收缩，当需要编码POJO对象大于ByteBuffer的容量时，会发送索引越界异常

- 2、ByteBuffer只有一个标识位置的指针position，读写的时候需要手工调用flip() 和rewind()等

- 3、ByteBuffer的API功能有限，一些高级和实用的特性他不支持。

  为了弥补这些不足，Netty提供了自己的ByteBuffer实现——**ByteBuf**

  

#### 二、ByteBuf的工作

​	不同ByteBuf实现类的工作原理不尽相同，首先ByteBuf依然是个Byte数组的缓冲区，他都基本功能应该与JDK的ByteBuffer一致，提供以下几类基本功能。

- 1、7种Java基础类型、byte数组、ByteBuffer等的读写
- 2、缓冲区自身的copy和slice等
- 3、设置网络字节序
- 4、构造缓冲区实例
- 5、操作位置指针等方法。

由于JDK的ByteBuffer已经提供了这些基础能力的实现，因此，NettyByteBuf的实现可以有两种策略

- 参考JDKByteBuffer的实现，增加额外的功能，解决原ByteBuffer的缺点。
- 聚合JDKByteBuffer，通过Gacade模式对其进行包装，可以减少自身的代码量，降低实现成本。