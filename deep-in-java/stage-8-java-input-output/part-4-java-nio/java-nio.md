# Java NIO

## 内容提要

* 缓冲（Buffers）
* 管道（Channels）
* 选择器（Selectors）



## 缓冲（Buffers）

### Buffers 简介

A ***buffer*** is an object that stores a fixed amount of data to be sent to or received from an ***I/O service*** (an operating system component for performing input/output). It sits between an application and a ***channel*** that writes the buffered data to the service or reads the data from the service and deposits into the buffer. 

### Buffer 属性

* capacity - 最大容量
* limit - 限定大小（数值小于等于 capacity）
* position - 读写下个缓冲元素的数组索引（数值小于等于 limit）
* mark - 记录当前位置（数值小于等于 position）

数值关系：0 <= mark <= position <= limit <= capacity

### Buffer 创建

* ByteBuffer allocate(int capacity)
* ByteBuffer allocateDirect(int capacity)
* ByteBuffer wrap(byte[] array)
* ByteBuffer wrap(byte[] array, int offset, int length)

### Buffer 存储

* public abstract byte get()
* public abstract byte get(int index)
* public abstract ByteBuffer put(byte b)
* public abstract ByteBuffer put(int index, byte b)

### Buffer 抛出（Flipping）

* public Buffer flip()
  * limit 设置为 position
  * position 设置为 0
  * mark = -1

### Buffer 倒带（Rewind）

* public Buffer rewind()
  * position 设置为 0
  * mark = -1

### Buffer 压缩（Compact）

* public ByteBuffer compact()

### Buffer 标记（Marking）

* public Buffer mark()
  * mark 设置为 position

### Buffer 复制（duplicate）

* public ByteBuffer duplicate()

### 字节顺序（Byte Order）

* java.nio.ByteOrder
  * Big-endian
  * Little-endian



## 管道（Channels）

### Channel API

* java.nio.channels.Channel
  * java.nio.channels.InterruptibleChannel
  * java.nio.channels.ReadableByteChannel
    * java.nio.channels.ScatteringByteChannel
  * java.nio.channels.WritableByteChannel
    * java.nio.channels.GatheringByteChannel



## 选择器（Selectors）

### 核心 API

* java.nio.channels.Selector 
  * manages information about a set of registered channels and their readiness states.
* java.nio.channels.SelectableLChannel
  * provides the common methods needed to implement channel selectability.
* java.nio.channels.SelectionKey 
  * encapsulates the registration relationship between a specific channel and specific selector.



























































































































































