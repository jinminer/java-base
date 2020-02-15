# methods common to all objects

所有对象都使用通用方法

## 重写 equals 方法时遵守通用约定

### equals 方法

* 不需要重写的场景
  * 每个类的实例都是固有唯一的。 对于像 `Thread` 这样代表活动实体而不是值的类来说，这是正确的。 `Object` 提供的 `equals` 实现对这些类完全是正确的行为。
  * 类不需要提供一个“逻辑相等（`logical equality`）”的测试功能。例如 `java.util.regex.Pattern` 可以重写
    `equals` 方法检查两个是否代表完全相同的正则表达式 `Pattern` 实例，但是设计者并不认为客户需要或希望使用此功能。在这种情况下，从 `Object` 继承的 `equals` 实现是最合适的。
  * 父类已经重写了 `equals` 方法，则父类行为完全适合于该子类。例如，大多数 `Set` 从 `AbstractSet` 继承了 `equals` 实
    现、List 从 `AbstractList` 继承了 `equals` 实现，`Map` 从 `AbstractMap` 的 `Map` 继承了 `equals` 实现。
  * 类是私有的或包级私有的，可以确定它的 `equals` 方法永远不会被调用。如果你非常厌恶风险，可以重写 `equals`方法，以确保不会被意外调用：

* 需要重写的场景
  * 如果一个类包含一个逻辑相等（ logical equality）的概念，此概念有别于对象标识（object identity），而且父类还没有重写过 equals 方法。
  * 通常用在值类（ value classes）的情况。
* 实例控制（instance control）的类
  * 一种不需要 equals 方法重写的值类是使用实例控制（instance control）的类，以确保每个值至多存在
    一个对象。
  *  对于这些类，逻辑相等与对象标识是一样的，所以 Object 的 equals方法作用逻辑 equals 方法。
  *  枚举类型属于这个类别。

* 重写 equals 方法时，必须遵守通用约定。
  * Object 的规范如下： equals 方法实现了一个等价关系（equivalence relation）
    * 自反性： 对于任何非空引用 x， `x.equals(x)` 必须返回 true。
    * 对称性： 对于任何非空引用 x 和 y，如果且仅当 `y.equals(x)` 返回 true 时 `x.equals(y)` 必须返回true。
    * 传递性： 对于任何非空引用 x、y、z，如果 `x.equals(y)` 返回 true， `y.equals(z)` 返回 true，则
      `x.equals(z)` 必须返回 true。
    * 一致性： 对于任何非空引用 x 和 y，如果在 equals 比较中使用的信息没有修改，则 `x.equals(y)` 的多次调用必须始终返回 true 或始终返回 false。
    * 对于任何非空引用 x， `x.equals(null)` 必须返回 false。
* 高质量重写 equals 方法的要点
  * 使用 == 运算符检查参数是否为该对象的引用。如果是，返回 true。这只是一种性能优化，但是如果这种比较可能很昂贵的话，那就值得去做。
  * 使用 `instanceof` 运算符来检查参数是否具有正确的类型。 如果不是，则返回 false。 通常，正确的类型是equals 方法所在的那个类。 有时候，改类实现了一些接口。 如果类实现了一个接口，该接口可以改进 equals 约定以允许实现接口的类进行比较，那么使用接口。 集合接口（如 Set，List，Map 和 `Map.Entry`）具有此特性。
  * 参数转换为正确的类型。因为转换操作在 `instanceof` 中已经处理过，所以它肯定会成功。
  * 对于类中的每个“重要”的属性，请检查该参数属性是否与该对象对应的属性相匹配。如果所有这些测试成功，返回 true，否则返回 false。如果步骤 2 中的类型是一个接口，那么必须通过接口方法访问参数的属性;如果类型是类，则可以直接访问属性，这取决于属性的访问权限。
* 基本类型处理
  * 对于类型为非 float 或 double 的基本类型，使用 == 运算符进行比较；
  * 对于对象引用属性，递归地调用 equals 方法；
  * 对于 float 基本类型的属性，使用静态 `Float.compare(float, float)` 方法；
  * 对于 double 基本类型的属性，使用 `Double.compare(double, double)` 方法。
  * 由于存在 `Float.NaN` ，`-0.0f` 和类似的 double 类型的值，所以需要对 float 和 double 属性进行特殊的处理；
  * 对于数组属性，将这些准则应用于每个元素。 如果数组属性中的每个元素都很重要，请使用其中一个重载的 `Arrays.equals` 方法。
* * 

* 注意
  * 当重写 `equals` 方法时，同时也要重写 `hashCode` 方法
  * 不要让 `equals` 方法试图太聪明。
  * 在 `equal` 时方法声明中，不要将参数 `Object` 替换成其他类型。
    * 问题在于这个方法并没有重写 `Object.equals` 方法，它的参数是 Object 类型的，这样写只是重载了 equals 方法
    * 即使除了正常的方法之外，提供这种“强类型”的 equals 方法也是不可接受的，因为它可能会导致子类
      中的 Override 注解产生误报，提供不安全的错觉。

## 重写 `equals` 方法时同时也要重写 `hashcode` 方法

### 定义

* **在每个类中，在重写 `equals` 方法的时侯，一定要重写 `hashcode` 方法。** 如果不这样做，你的类违反了 `hashCode` 的通用约定，这会阻止它在 `HashMap` 和 `HashSet` 这样的集合中正常工作。根据 Object 规范，以下时具体约定。

