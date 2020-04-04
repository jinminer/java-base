# Java APT

## 内容提要

* Java APT
* Language Model API
* Annotation Processing Tool API



Java APT

### Java APT 基础

#### 什么是 Java APT

The command-line utility `apt`, annotation processing tool, finds and executes *[annotation processors](https://docs.oracle.com/javase/6/docs/technotes/guides/apt/GettingStarted.html#AnnotationProcessor)* based on the annotations present in the set of specified source files being examined. The annotation processors use a set of reflective APIs and supporting infrastructure to perform their processing of program [annotations](https://docs.oracle.com/javase/6/docs/technotes/guides/language/annotations.html) ([JSR 175](http://www.jcp.org/en/jsr/detail?id=175)). The `apt` reflective APIs provide a build-time, source-based, read-only view of program structure. These reflective APIs are designed to cleanly model the JavaTM programming language's type system after the addition of [generics](https://docs.oracle.com/javase/6/docs/technotes/guides/language/generics.html) ([JSR 14](http://www.jcp.org/en/jsr/detail?id=14)). First, `apt` runs annotation processors that can produce new source code and other files. Next, `apt` can cause compilation of both original and generated source files, thus easing the development cycle.

#### 为什么要用 Java APT

Many of the intended use cases for annotations involve having annotations in a base file hold information that is used to generate new derived files (source files, class files, deployment descriptors, etc.) that are logically consistent with the base file and its annotations. In other words, instead of manually maintaining consistency among the entire set of files, only the base file would need to be maintained since the derived files are generated. The `apt` tool is designed for creating the derived files.

Compared to using a doclet to generate the derived files based on annotations, `apt`

- has a cleaner model of the declarations and current type structure of programs
- uses a more contemporary API design, such as returning generic collections instead of arrays and providing visitors to operate on declarations and types
- supports recursive processing of newly generated files and can automatically cause compilation of original and generated source files

#### APT 外部依赖

* Java 编译环境：javac 工具或者 Java Compiler API
* Java Language Model API 
* Java Annotation Processing Tool APi











































