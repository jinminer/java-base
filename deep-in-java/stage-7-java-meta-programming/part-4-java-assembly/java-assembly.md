# Java 字节码操作

## 内容提要

* Java 字节码基础
* Java 字节码操作框架
* Java Instrumentation



## Java 字节码基础

### Java 类文件

A `class` file consists of a stream of 8-bit bytes. 16-bit and 32-bit quantities are constructed by reading in two and four consecutive 8-bit bytes, respectively. Multibyte data items are always stored in big-endian order, where the high bytes come first. *In the Java SE Platform API, the* `class` *file format is supported by interfaces* `java.io.DataInput` *and* `java.io.DataOutput` *and classes such as* `java.io.DataInputStream` *and* `java.io.DataOutputStream`*.* 

### Java 类文件结构

![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-7-java-meta-programming/part-4-java-assembly/1.0-java-class-file-structure.png)



## Java 字节码操作框架

### 常见框架

* ASM - https://asm.ow2.io
* AspectJ - https://www.eclipse.org.aspectj/
* BECL - http://commons.apache.org/proper/commons-bcel/
* CGLIB -  http://github.com/cglib/cglib
* Javassist - http://www.javassist.org/

### ASM

语法树分析 - Visitor

> PS: javac、JSP、ASM、XML、HTML

* JSP - jesper
* 字节码 - ASM
* Java 源码编译 - javac、Eclipse Compiler
* XML - SAX、JAXB、DOM

#### 实现方式

* 基于事件实现

  * ASM
  * SAX

* 基于树形结构

  * ASM

  * DOM

    ```XML
    <root>
    	<name value=""></name>
    </root>
    ```

    

## Java Instrumentation

### 什么是 Instrumentation

The `java.lang.instrument` package provides a Java™ programming language API for tools to instrument Java programming language applications -- for example, to monitor them or collect performance information. Tools use `java.lang.instrument` to modify the class file that defines a class -- generally, by inserting into the byte-code of methods additional byte-code which will perform the instrumentation.

`java.lang.instrument` was introduced in JDK™ 5.0.





























































