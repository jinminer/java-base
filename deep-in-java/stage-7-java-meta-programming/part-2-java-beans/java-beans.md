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

#### Java 事件对象（Event Objects）

Information associated with a particular event notification is normally encapsulated in an "***event state***" object that is a subclass of ***java.util.EventObject***. By convention these event state classes are given names ending in "***Event***".

#### Java 事件监听器（Event Listeners）

Since the new Java event model is based on method invocation we need a way of defining and grouping ***event handling methods***. We require that event handling methods be defined in EventListener interfaces that inherit from ***java.util.EventListener***. By convention these EventListener interfaces are given names ending in "***Listener***".

事件监听器注册（Event Listener Registration）

In order for potential ***EventListeners*** to register themselves with approppriate event source instances, thus establishing an event flow from the source to the listener, event source classes must provide methods for ***registering*** and ***de-registering*** event listeners.

The standard design pattern for ***EventListener*** registration is:

*  `public void add<ListenerType>(<ListenerType> listener);`
*  `public void remove<ListenerType>(<ListenerType> listener);`

#### 事件传递语义（Event Delivery Semantics）

* 单播/广播（Unicast/Multicast）
* 同步传递（Synchronous delivery）
* 异常条件（Exceptional Conditions）
* 并发控制（Concurrency control）
* 传递过程修改监听器集合（Modifications to the Set of Evnet Listeners during Event Delivery）

### Java Beans 属性（Properties）

#### 访问者方法（Accessor Methods）

Properties are always accessed via method calls on their owning object. For ***readable properties*** there will be a ***getter*** method to read the property value. For ***writable properties*** there will be a ***setter*** method to allow the property value to be updated.

* 可写方法（Writable Methods）：setter 方法
* 可读方法（Readable Methods）：getter、isXXX 方法

#### 索引属性（Indexed Properties）

An indexed property supports a range of value. Whenever the property is read or written you just specify an index to identify which value you want. Property indexes must be Java "***int***"s.

* void setter(int index, PropertyType value); // indexed setter
* PropertyType getter(int index); // indexed getter
* void setter(PropertyType values[]); // array setter
* PropertyType[] getter(); // array getter

#### 强迫属性（Bound Properties）

Sometimes when a bean property changes then either the bean's container or some other bean may wish to be notified of the change. If a bean supports bound properties then it should ***PropertyChangeListeners***:

* public void addPropertyChangeListener(PropertyChangeListener x);
* public void removePropertyChangeListener(PropertyChangeListener x);

#### 勉强属性（Constrained Properties）

Sometimes when a property change occurs some other bean may wish to validate the change and reject it if it si inappropriate. If a bean supports constrained properties then it should support a normal pair of multicast event listener registration methods for ***VetoableChangeListeners***:

* public void addVetoableChangeListener(VetoableChangeListener x);
* public void removeVetoableChangeListener(VetoableChangeListener x);

### Java Beans 内省（Introspection）

#### 基础知识

At runtime and in the builder environment we need to be able to figure out which ***properties***, and ***methods*** a Java Bean supports. We call this process ***introspection***.

By default we will use a low level ***reflection*** mechanism to study the methods supported by a target bean and then apply simple ***design patterns*** to deduce from those methods what properties, events, and public methods are supported.

#### Java Beans 设计模式（Design Patterns）

* 属性设计模式
  * 简单属性（Simple properties）
  * Boolean 属性（Boolean properties）
  * 索引属性（Indexed properties）
* 事件设计模式
  * 单播事件源（Unicast event sources）
* 方法设计模式
  * 所有 public 方法，包括 setter 和 getter 方法

#### Java Beans 内省器 - java.beans.Intrespector

* Bean 信息类 - java.beans.BeanInfo
* Bean 描述符 - java.beans.BeanDescriptor
* Bean 属性描述符 - java.beans.PropertyDescriptor
* Bean 方法描述符 - java.beans.MethodDescriptor
* Bean 事件集合描述符 - java.beans.MethodDescriptor

### Java Beans 自定义（Customization）

#### Java Beans 自定义

* 属性修改器 - java.beans.PropertyEditor
* 属性修改器实现 - java.beans.PropertyEditorSupport
* 属性修改器管理器 - java.beans.PropertyEditorManager
* GUI 组件自定义器 - java.beans.Customizer

### Java Beans 持久化（Persistence）

#### Java 标准序列化

* 默认序列化实现 - 实现 java.io.Serializable 接口
* 选择性序列化实现 - 实现 writeObject 和 readObject 方法
* 自定义序列化实现 - java.io.Externalizable 接口



## BeanContext 规范



































































































