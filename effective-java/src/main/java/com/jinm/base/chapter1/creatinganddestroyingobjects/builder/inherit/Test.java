package com.jinm.base.chapter1.creatinganddestroyingobjects.builder.inherit;

public class Test {

    public static void main(String[] args) {

        NyPizza pizza= new NyPizza.Builder(NyPizza.Size.SMALL).addTopping (NyPizza.Topping.SAUSAGE).addTopping(NyPizza.Topping.ONION).build();
        Calzone calzone =new Calzone.Builder().addTopping(Pizza.Topping.HAM).sauceInside(). build();

    }

}
