# Java Beans

## 内容提要

* Java Beans 基础
* Java Beans 核心特性
* BeanContext 规范



## Java Beans 基础

### 什么是 Java Beans

* "A Java Bean is a reusable software component that can be manipulated visually in a builder tool."
* Support for "introspection" so that a builder tool can analyze how a bean works
* Support for  "customization" so that when using an application builder a user can customize the appearance and behavior of a bean.
* Support for  "events" as a simple communication metaphor than can be used to connect up beans.

* Support for "properties", both for customization and for programmatic use.
* Support for persistence, so that a bean can be customized in an application builder and then have its customized state saved away and reloaded later.

### Java Beans 核心特性

* 事件（Evens）
* 属性（Properties）
* 自省（Introspection）
* 自定义（Customization）
* 持久化（Persistence）

### 相关规范

* BeanContext Specification
  * https://docs.oracle.com/javase/8/docs/technotes/guides/beans/spec/beancontext.html
* JavaBeans Specification
  * https://www.oracle.com/techenetwork/java/javase/tech/index-jsp-138795.html



## Java Beans 核心特性

### Java 事件（Events）

Events are one of the core features of the Java Beans architecture. Events provide a convenient mechanism for allowing components to be plugged together in an application builder, by allowing some components to act as source for ***event notifications*** that can then be caught and processed by either scripting environment or by other components.

Conceptually, events are a mechanism for propagating state change notifications between a source object and one or more target ***listener*** objects. Events have many different uses, but a common example is their use in window system toolkits for delivering notifications of mouse actions, widget updates, keyboard actions, etc.

The details of the event model are described the following sections, but in outline:

* Event notifications are propagated from ***sources*** to listeners by Java method invocations on the target listener objects.
* Each distinct kind of event notification is defined as a distinct Java method. These methods are then grouped in EventListener interfaces that inhert from ***java.util.EventListener***.
* Event listener classes ***identify*** themselves as interested in a particular set of events by implementing some set of EventListener interfaces.
* The state associated with an event notification is normally encapsulated in an event state object that inherits from ***java.util.EventObject*** and which is passed as the sole argument to the event method.
* Event sources ***identify*** themselves as sourcing particular events by defining registration methods that conform to a specific design pattern and accept references to instances of partivular EventListener interfaces.
* In circumstances where listeners cannot directly implement a particular interface, or when some additional behavior is required, an instance of acustom adaptor class may be interposed between a source and one or more listeners in order to establish the relationship or to augment behavior.







## BeanContext 规范



































































































