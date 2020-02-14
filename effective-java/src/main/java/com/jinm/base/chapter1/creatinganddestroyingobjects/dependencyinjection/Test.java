package com.jinm.base.chapter1.creatinganddestroyingobjects.dependencyinjection;

public class Test {

    private final Test1 test1;

    public Test(Test1 test1) {
        this.test1 = test1;
    }

    @Override
    public String toString() {
        return "Test{" +
                "test1=" + test1 +
                '}';
    }

    public static void main(String[] args) {

        Test test = new Test(new Test1());
        Test test2 = new Test(new Test1());


    }
}
