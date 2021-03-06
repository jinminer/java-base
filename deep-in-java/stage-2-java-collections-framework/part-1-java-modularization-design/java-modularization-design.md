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

## Java 模块化核心概念

### 模块路径（Module Path）

模块路径可能是单个 artifact， 或者是多个 artifacts 的目录，存在于宿主机器上。

* 类路径（Class Path）的脆弱性
  * 通过 artifacts 的 Class Path 区分类型
  * 无法区分 artifacts
  * 无法提前通知 artifacts 缺少
  * 允许不同的 artifacts 定义在相同的 packages 定义类型

### 模块路径的差异性

* 定位整个模块而非类型
* 无论是运行时，还是编译时，在同一目录下不允许出现同名模块

### 模块解析（Resolution）

* 假设当前模块 com.foo.app 如下定义：

```java
module com.foo.app{
    requires com.foo.bar;
    requires java.sql;
}
```

* com.foo.app 依赖于 java.sql 模块，而该模块又存在 java.logging 和 java.xml 依赖：

```java
module java.sql{
    requires java.logging;
    requires java.xml;
    exports java.sql;
    exports javax.sql;
    exports javax.transaction.xa;
}
```

* Java 模块看化系统将会解析模块 com.foo.app 完整的依赖树：

![1.0-module-dependency-tree](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-2-java-collections-framework/part-1-java-modularization-design/1.0-module-dependency-tree.png)



### 可读性（Readability）

* 解析

  * 如上图所示，模块 com.foo.app 依赖模块 com.foo.bar 和 java.sql，说明 java.sql 对 com.foo.app 是读的。
  * 同时，java.sql 依赖 java.xml 和 java.logging 模块，然而这并不意味着 java.xml 或 java.logging对 com.foo.app 可读。
  * 简而言之，可读性无法在跨层模块之间生效。

* 结论

  * 这种在模块中定义的可读性关系是可靠配置的基础，这种配置不但更加可靠，同时也提升了模块读取速度。

    ![readable](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-2-java-collections-framework/part-1-java-modularization-design/1.1-readable.png)



### 访问性（accessible）

* 访问性依赖于强封装性
* 强封装性 = 可读性关系 + 模块 exports 声明



## Java 模块化迁移

### 迁移分析

* 需要明确应用实现依赖的 JDK 模块
* 需要明确二方或三方 jar 所依赖的 JDK 模块
* 需要微服务化应用

### 模块分类

#### 命名模块（Named modules）

* 所有正常的 Java 模块，packages 暴露受限于 exports
* 凡是定义 module-info.java(module-info.class)属于命名模块（Java 9 + 模块化 artifact）

#### 非命名模块（Unnamed module）

* 类型加载于 ClassPath，而非具体模块，如一六 jar 文件，暴露所有的 packages。
* Java 9 之前的 artifact 属于非命名模块（迁移老类库）

#### 自动模块（automatic module）

* 假设我们需要使用 Spring ListenableFuture API，它来自于 org.springframework:springcore，由于该 jar 文件属于非命名模块，并且其 artifactId 为 spring-core，该 ID 命名的方式对于模块名称是非法的。
* 我们能够在模块路径下能够使用“自动模块”代替 spring-core*.jar，即使用 spring.core 模块。
* 规则
  * 如果在 `MANIFEST.MF` 定义了 `Automatic-Module-Name` 属性，那么采用该属性值做模块名称
  * 否则，使用 jar 文件的名称（如果存在“-”的话，将其替换为“.”）
* 否则，使用 jar 



## Java 模块化反射

### 获取模块

* 获取模块 - Class#getModule()
* 模块接口 - Module
* 模块描述文件接口 - ModuleDescriptor



































