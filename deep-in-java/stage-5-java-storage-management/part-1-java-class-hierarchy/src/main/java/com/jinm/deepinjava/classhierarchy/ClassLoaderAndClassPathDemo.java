package com.jinm.deepinjava.classhierarchy;

/**
 * ClassLoader 与 ClassPath 之间的关系示例代码
 */
public class ClassLoaderAndClassPathDemo {


    public static void main(String[] args) {
        // 通常，在 JVM 进程中添加 -verbose:class 参数来显示加载的 Class
        // 所在的位置（source），如：
        // [Loaded com.jinm.deepinjava.classhierarchy.ClassLoaderAndClassPathDemo from file:/D:/workspace/projects/advance/learning/java-base/deep-in-java/stage-5-java-storage-management/part-1-java-class-hierarchy/target/classes/]
        // Bootstrap ClassLoader 加载的 Class 将会抛出 java.lang.NullPointerException
//        getClassLocation(Object.class);
//        getClassLocation(int.class);
        // 类资源与 URL 有关联，是否意味着 ClassLoader 与 URL 存在关联
        getClassLocation(ClassLoaderAndClassPathDemo.class);
        // Spring Boot spring-boot-loader
        // 文件目录：Expose -> File Handler
        // 文件：JAR、WAR、EAR Jar Handler
        // URL 抽象 Java 资源管理


    }

    private static void getClassLocation(Class<?> klass) {
        System.out.printf("类[%s] 资源所在的位置：%s\n", klass,
                klass.getProtectionDomain().getCodeSource().getLocation());
    }
}
