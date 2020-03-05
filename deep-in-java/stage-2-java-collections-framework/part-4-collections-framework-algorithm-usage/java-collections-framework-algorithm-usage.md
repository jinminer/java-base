# Java 集合框架算法运用

## 内容提要

* 排序算法
* Java 排序算法实现
* 二分搜索算法
* Java 二分搜索算法实现
* 总结



## 排序算法

### 基本介绍

An algorithm that puts elements of a list in a certain order. The most frequently used orders are numerical order and lexicographical order. Efficient sorting is important for optimizing the efficiency of other algorithms (such as search and merge algorithms) which require input data to be in sorted lists. Sorting is also often useful for canonicalizing data and for producing human-readable output. More formally, the output of any sorting algorithm must satisfy two conditions:

* The output is nondecreasing order (each element is no smaller than the previous element according to the desired total order);
* The output is a permutation (a reordering, yet retaining all of the original elements) of the input.

### 分类

* 计算复杂度：最佳、最坏以及平均复杂度
* 内存使用：空间复杂度
* 递归算法：排序算法中是否用到了递归
* 稳定性：当相同的键存在时，经过排序后，其值也保持相对的顺序（不发生变化）
* 比较排序：集合中的两个元素进行比较排序
* 串行或并行：是否运用串行或并行排序

### 计算复杂度（Computational Complexity）

The amount of resources required for running it. The computational complexity of a problem is the minimum of the complexities of all possible algorithms for this problem (including the unknown algorithms).

As the amount of needed resources varies vith the input, the complexity is generally expressed as a function `n → f(n)`, where `n` is the size of the input, and `f(n)` is either the worst-case complexity, that is the maximum of the amount of resources that are needed for all inputs of size `n`, or the average-case complexity, that is average of the amount of resources over all input of size `n`.

When the nature of the resources is not explicitly given, this is usually the time needed for running the algorithm, and one talks of `time complexity`. However, this depends on the computer that is used, and the time is generally expressed as the number of needed elementary operations, which are supposed to take a constant time on a given computer, and to  change by a constant factor when are supposed to take a constant time on a given computer, and to change by a constant factor when one changes of computer.

Otherwise, the resource that is considered is often the size of the memory that is needed, and one talks of `space complexity`.

### 时间复杂度（Time Complexity）

The amount of time it takes to run an algorithm. Time complexity is commonly estimated by counting the number of elementary operations performed by the algorithm, supposing that each elementary operation takes a fixed amount of time to perform. Thus, the amount of time taken and the number of elementary operations performed by the algorithm are taken to differ by at most a constant factor.

Since an algorithm's running time may vary among different inputs of the same size, one commonly considers the [worst-case time complexity](https://en.wikipedia.org/wiki/Worst-case_complexity), which is the maximum amount of time required for inputs of a given size. Less common, and usually specified explicitly, is the [average-case complexity](https://en.wikipedia.org/wiki/Average-case_complexity), which is the average of the time taken on inputs of a given size (this makes sense because there are only a finite number of possible inputs of a given size). In both cases, the time complexity is generally expressed as a [function](https://en.wikipedia.org/wiki/Function_(mathematics)) of the size of the input.[[1\]](https://en.wikipedia.org/wiki/Time_complexity#cite_note-Sipser-1):226 Since this function is generally difficult to compute exactly, and the running time for small inputs is usually not consequential, one commonly focuses on the behavior of the complexity when the input size increases—that is, the [asymptotic behavior](https://en.wikipedia.org/wiki/Asymptotic_analysis) of the complexity.

#### 表达式：Big O notation

* 常量时间：T(n) = O(1) (数组随就访问)
* 线性时间：T(n) = O(n) (在未排序数组中找寻最值)
* 对数时间：T(n) = O(log n) (二进制搜索)
* 指数时间：T(n) = O(n^c) (冒泡排序、插入排序)

#### Big O notation (O)

* 表达式

  * 无限渐进（Infinite asymptomatic）：T(n) = O(n^2)

  * 无限小渐进（Infinitesimal asymptomatic）

    ![big-o-notation](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-2-java-collections-framework/part-4-collections-framework-algorithm-usage/1.0-big-o-notation.png)

### 稳定性（Stability）

Stable sort algorithms sort repeated elements in the same order that they appear in the input. When sorting some kinds of data, only part of the data is examined when determining the sort order. For example, in the card sorting example to the right, the cards are being sorted by their rank, and their suit is being ignored. This allows the possibility of multiple different correctly sorted versions of the original list. Stable sorting algorithms choose one of these, according to the following rule: if two items compare as equal, like the two 5 cards, then their relative order will be preserved, so that if one came before the other in the input, it will also come before the other in the output.

#### 稳定排序

![stability](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-2-java-collections-framework/part-4-collections-framework-algorithm-usage/2.0-stability.png)

#### 非稳定排序

![unstability](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-2-java-collections-framework/part-4-collections-framework-algorithm-usage/3.0-unstability.png)

### 比较排序

A **comparison sort** is a type of [sorting algorithm](https://en.wikipedia.org/wiki/Sorting_algorithm) that only reads the list elements through a single abstract comparison operation (often a "less than or equal to" operator or a [three-way comparison](https://en.wikipedia.org/wiki/Three-way_comparison)) that determines which of two elements should occur first in the final sorted list. The only requirement is that the operator forms a [total preorder](https://en.wikipedia.org/wiki/Total_preorder) over the data, with:

1. if *a* ≤ *b* and *b* ≤ *c* then *a* ≤ *c* (transitivity)
2. for all *a* and *b*, *a* ≤ *b* or *b* ≤ *a* ([connexity](https://en.wikipedia.org/wiki/Connex_relation)).

#### 分类

* 冒泡排序（Bubble Sort）：最佳 O(n)、平均 O(n^2)、最坏 O(n^2)
* 插入排序（Insertion Sort）：最佳 O(n)、平均 O(n^2)、最坏 O(n^2)
* 快速排序（Quick Sort）：最佳 O(nlogn)、平均 O(nlogn)、最坏 O(n^2)
* 合并排序（Merge Sort）：最佳 O(nlogn)、平均 O(nlogn)、最坏 O(nlogn)
* Tim 排序（Tim Sort）：最佳 O(n)、平均 O(nlogn)、最坏 O(nlogn)

#### 冒泡排序（Bubble Sort）

#### 插入排序（Insertion Sort）

#### 快速排序（Quick Sort）

* 步骤
  * 获取 pivot（轴）
  * 分区（Partitioning）
  * 递归执行

#### 合并排序（Merge Sort）

* 步骤
  * 分块（Divide）
  * 递归合并（Conquer）



## Java 集合框架排序算法实现

### 内建实现

* 冒泡排序（Bubble Sort）：无
* 插入排序（Insertion Sort）：java.util.Arrays#mergeSort（当排序集合数量小于 7 时）
* 快速排序（Quick Sort）：java.util.DualPivotQuickSort#sort（since 1.7）
* 合并排序（Merge Sort）：java.util.Arrays#mergeSort（1.7 后需要激活）
* Tim 排序（Tim Sort）：java.util.TimSort（Since 1.7）



## 二分搜索算法

### 二分搜索（Binary Search）

Also known as **half-interval search**,[[1\]](https://en.wikipedia.org/wiki/Binary_search_algorithm#cite_note-Williams1976-1) **logarithmic search**,[[2\]](https://en.wikipedia.org/wiki/Binary_search_algorithm#cite_note-FOOTNOTEKnuth1998§6.2.1_("Searching_an_ordered_table"),_subsection_"Binary_search"-2) or **binary chop**,[[3\]](https://en.wikipedia.org/wiki/Binary_search_algorithm#cite_note-FOOTNOTEButterfieldNgondi201646-3) is a [search algorithm](https://en.wikipedia.org/wiki/Search_algorithm) that finds the position of a target value within a [sorted array](https://en.wikipedia.org/wiki/Sorted_array).[[4\]](https://en.wikipedia.org/wiki/Binary_search_algorithm#cite_note-FOOTNOTECormenLeisersonRivestStein200939-4)[[5\]](https://en.wikipedia.org/wiki/Binary_search_algorithm#cite_note-5) Binary search compares the target value to the middle element of the array. If they are not equal, the half in which the target cannot lie is eliminated and the search continues on the remaining half, again taking the middle element to compare to the target value, and repeating this until the target value is found. If the search ends with the remaining half being empty, the target is not in the array.

### 步骤

1. 假设 A 表示数组，V 表示搜索的数据，设置 L（低位）=0，H（高位）= 数组长度 - 1
2. 如果 L > H 的话，搜索结果
3. 设置 M（中位）= L + H / 2
4. 如果 A[M] < V，设置 L = M + 1，重新执行步骤二
5. 如果 A[M] > V，设置 L = M - 1，重新执行步骤二
6. 当 A[M] = V 时，搜索结束，返回 M

### 类比算法

* Hash 算法
* Trees 算法
* 线性搜索

### Java 二分搜索算法实现

内建实现

* java.util.Arrays#binarySearch
* java.util.Collections#binarySearch
* java.util.TreeMap



















