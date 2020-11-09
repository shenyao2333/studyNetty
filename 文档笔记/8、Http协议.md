### HTTP协议开发应用

​	HTTP（超文本传输协议）协议是建立在TCP传输协议之上的应用层协议，它的发展是万维网协会和Internet工作小组IETF合作的结果。HTTP是一个属于应用层的面向对象的协议，由于其简洁、快速的方式，适用于分布式超媒体信息系统。是目前Web开发的主流协议，基于HTTP的应用非常广泛，因此，掌握HTTP的开发非常重要。



#### 一、HTTP协议介绍

##### 	1、HTTP协议主要特点如下

- 支持Client/Server模式

- 简单——客户向服务器请求服务时，只需要指定服务URL，携带必要的请求参数或者消息体

- 灵活——HTTP允许传输任意类型的数据对象，传输的内容类型由HTTP消息头中的Content-Type加以标记

- 无状态——HTTP协议是无状态协议，无状态是指协议对于事务处理没有记忆能力。缺少状态意味着如果后续处理需要之前的信息，则他必须重传，这样可能导致每次连接传送的数据量增大。另一方面，在服务器不需要先前信息时它的应答就比较快，负载较轻

  
  

##### 2、HTTP协议的URL

​	HTTP URL（URL是一种特殊类型的URI，包含了用于查找没个资源的足够信息）如下

```http
http://host[":"post][abs_path]
```

​	http表示要通过HTTP协议来定位网络资源；host表示合法的Internet主机域名或者IP地址；port指定一个端口，为空则使用模式端口80；abs_path指定请求资源的URI，如果URL中没有给出abs_path，那么当它作为请求URI时，必须以"/"的形式给出，通常这点工作游览器自动帮我们完成



##### 3、HTTP请求消息

- HTTP请求行
- HTTP消息体
- HTTP请求正文

​      请求行以一个方法开头，一空格分开，后面跟着请求的URI和协议的版本，格式为Method Request-URI HTTP-Version CRLF。
​     其中Method表示请求方法，Request-URI是一个统一资源标识符号，HTTP-Version表示请求的HTTP协议版本，CRLF表示回车和换行

- GET：请求获取Request-URI所标识的资源
- POST：在Request-URI所标识的资源后附加新的提交数据
- HEAD：请求获取与由Request-URI所表示的的资源响应消息报头
- PUT：请求服务器存储一个资源，并且用Request-URI作为标识
- DELETE：请求服务器删除Request-URI所标识的资源
- TRACE：请求服务器会送收到的请求消息

。。。。。。。。。



### 二、NettyHTTP服务端入门



​	