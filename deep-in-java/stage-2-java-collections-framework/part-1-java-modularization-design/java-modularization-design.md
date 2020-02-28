# Java 模块化设计

## 内容提要

* Java 模块化基础
* Java 模块化核心概念
* Java 模块化迁移
* Java 模块化反射
* 总结



## Java 模块化基础

### Java 9 模块化

#### 动机

* 强封装的实施与精确的模块依赖声明使得大型应用和框架更好的维护
* 安全提升
* 增快应用模块中类型检测的速度，提升应用性能
* 瘦身 JDK 以及 SE 的体积，有利于在小型计算设备使用和云端部署

### 定义模块

#### 模块声明

> A module's self-description is expressed in its module declaration, a new construct of the Java programming language.

```java
module java.compiler{
    exports javax.annotation.processing;
    exports javax.lang.model;
    exports javax.lang.model.element;
    exports javax.lang.model.type;
    exports javax.tools;
    
    uses javax.tools.DocumentationTool;
    uses Javax.tools.JavaCompiler;
}
```

#### 模块依赖

> One or more requires clauses can be added to declare that the module depends, by name, upon some other modules, at both compile time and run time:

```java
module java.sql{
    requires transitive java.logging;
    requires transitive java.transaction.xa;
    requires transitive java.xml;
    
    exports java.sql;
    exports javax.sql;
    
    uses java.sql.Driver;
}
```

#### 模块导出

> exports clauses can be added to declare that the module makes all, and only, the public types in specific packkages available for use by other modules:

```java
module java.sql{
    requires transitive java.logging;
    requires transitive java.transaction.xa;
    requires transitive java.xml;
    
    exports java.sql;
    exports javax.sql;
    
    uses java.sql.Driver;
}
```

























































