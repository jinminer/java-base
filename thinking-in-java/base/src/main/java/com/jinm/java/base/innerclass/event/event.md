
# 事件
## 基于事件的程序结构
> 在事件程序结构的所有设计中，不能准确地知道一个“事件”要做什么。这正是整个设计的关键；
  它怎样“将发生变化的东西同没有变化的东西区分开”？或者说，“改变的意图”造成了各类
  Event 对象的不同行动。我们通过创建不同的Event 子类，从而表达出不同的行动。

## 事件控制机制
* **在单独一个类里表达一个控制框架应用的全部实施细节，从而完整地封装与那个实施有关的所有东西。**
内部类用于表达多种不同类型的action()，它们用于解决实际的问题。除此以外，还使用了
private 内部类，所以实施细节会完全隐藏起来，可以安全地修改。
* **内部类使我们具体的实施变得更加巧妙，因为能方便地访问外部类的任何成员。**若不具备这种能力，代
码看起来就可能没那么使人舒服，最后不得不寻找其他方法解决。
* 注意在基于事件的程序内部类的实现看起来很类似于多重继承：
    内部类继承了事件抽象类，同时具有外部类属性、方法的访问权限

