package com.jinm.deepinjava.concurrent.advanced.usage;

public class ReferenceDemo {

    public static void main(String[] args) {

//        Entity entity = new Entity(1);
//        System.out.println("调用前：" + entity);
//        new ReferenceDemo().method(entity);
//        System.out.println("调用后：" + entity);

        String string = "123";
        System.out.println("调用前：" + string);
        new ReferenceDemo().string(string);
        System.out.println("调用后：" + string);

    }

    private void method(Entity entity){
        entity = new Entity(2);
        System.out.println("方法内：" + entity);
    }

    private void string(String string){
        string = string +"456";
        System.out.println("方法内：" + string);
    }

}

class Entity{

    int value;

    public Entity(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "value=" + value +
                '}';
    }
}
