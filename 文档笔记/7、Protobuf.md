#### 1、介绍

Protobuf是一个灵活、高效、结构化的数据序列化框架，相比与XML等传统的序列化工具，他更小、更快、更简单。Protobuf支持数据结构化一次可以到处使用，甚至跨语言使用。



#### 2、再次介绍优点

- 1、在谷歌内部长期使用，产品成熟度高
- 2、跨语言、支持多种语言
- 3、编码后消息更小，更加有利于存储和传输
- 4、编解码性能非常高
- 5、支持不同协议版本的向前兼容
- 6、支持定义可选和必选字段



#### 3、具体用法就不在这说明了



#### 4、简单使用说明

- 编码为byte数组

  编码时通过调用XX.XX实例的toByteArray即可编码为byte数组

- 解码

  编码时通过调用XX.XX的静态方法parseFrom将二进制byte数组解码为原始的对象。



#### 5、处理器使用说明

首先是先像ChannelPielne添加ProtobufVarint32FrameDecoder，它主要用于半包处理，然后继续添加ProtobufDecoder编码器，他的参数是定义的proto类，实际上就是要告诉ProttobufDecoder需要编码的目标类是什么。



#### 6、使用注意事项

​	ProtobufDecoder仅仅负责半包解码，他不支持读半包。因此，在ProtobufDecoder前面，一定要有能够处理读半包的解码器。比如：

- 1、使用Netty提供的ProtobufVarint32FrameDecoder，他可以处理半包消息
- 继承Netty提供的通用半包解码器LengthFieldBasedFrameDecoder
- 继承ByteToMessageDecoder类，自己处理半包消息

