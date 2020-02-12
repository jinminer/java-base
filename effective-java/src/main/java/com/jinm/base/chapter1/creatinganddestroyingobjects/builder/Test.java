package com.jinm.base.chapter1.creatinganddestroyingobjects.builder;

public class Test {

    public static void main(String[] args) {

        NutritionFacts nutritionFacts = new NutritionFacts.Builder(1,2)
                .calories(3)
                .carbohydrate(4)
                .fat(5)
                .sodium(6)
                .build();

        System.out.println(nutritionFacts);

    }

}
