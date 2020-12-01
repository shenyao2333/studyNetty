## Netty之AttributeKey的应用



### 一、概要

AttributeMap这是绑定在Channel或者ChannelHandler上的一个附件，相当于依附在这两个对象上的。如图：

![](./img/x-1.png)

​	每个ChannelHandlerContext都是ChannelHandler和ChannelPipeline之间桥梁，每一个ChannelHandlerContext都有一个属于自己的上下文，也就是说每个ChannelHandlerContext上如果有AttributeMap，那都是绑定上下文的，一个ChannelHandlerContext中的AttributeMap 另外一个是获取不到的

​	但是Channel上的AttributeMap就是大家共享的，每个ChannelHandler都能获取得到。





### 二、AttributeMap介绍

​	AttributeMap的结构和Map的格式很像，key是**AttributeKey**，value是Attribute，我们可以根据AttributeKey找到对应的Attribute，并且我们是可以指定Attribute的类型T

