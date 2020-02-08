package com.jinm.java.base.rtti.classsign;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * 类标记
 */
class Pet {}
class Dog extends Pet {}
class Pug extends Dog {}
class Cat extends Pet {}
class Rodent extends Pet {}
class Gerbil extends Rodent {}
class Hamster extends Rodent {}
class Counter { int i; }
public class PetCount2 {
    public static void main(String[] args) {
        Vector pets = new Vector();
        // Class literals work in Java 1.1+ only:
        Class[] petTypes = {Pet.class, Dog.class, Pug.class, Cat.class, Rodent.class, Gerbil.class, Hamster.class,};
        try {
            for(int i = 0; i < 15; i++) {
                // Offset by one to eliminate Pet.class:
                int rnd = 1 + (int)(Math.random() * (petTypes.length - 1));
                pets.addElement(petTypes[rnd].newInstance());
            }
        }catch(InstantiationException e) {
        }catch(IllegalAccessException e) {
        }
        Hashtable h = new Hashtable();
        for(int i = 0; i < petTypes.length; i++) {
            h.put(petTypes[i].toString(), new Counter());
        }
        for(int i = 0; i < pets.size(); i++) {
            Object o = pets.elementAt(i);
            if(o instanceof Pet) {((Counter)h.get("class com.jinm.java.base.rtti.classsign.Pet")).i++;}
            if(o instanceof Dog) {((Counter)h.get("class com.jinm.java.base.rtti.classsign.Dog")).i++;}
            if(o instanceof Pug) {((Counter)h.get("class com.jinm.java.base.rtti.classsign.Pug")).i++;}
            if(o instanceof Cat) {((Counter)h.get("class com.jinm.java.base.rtti.classsign.Cat")).i++;}
            if(o instanceof Rodent) {((Counter)h.get("class com.jinm.java.base.rtti.classsign.Rodent")).i++;}
            if(o instanceof Gerbil) {((Counter)h.get("class com.jinm.java.base.rtti.classsign.Gerbil")).i++;}
            if(o instanceof Hamster) {((Counter)h.get("class com.jinm.java.base.rtti.classsign.Hamster")).i++;}
        }
        for(int i = 0; i < pets.size(); i++) {
            System.out.println(pets.elementAt(i).getClass().toString());
        }
        Enumeration keys = h.keys();
        while(keys.hasMoreElements()) {
            String nm = (String)keys.nextElement();
            Counter cnt = (Counter)h.get(nm);
            System.out.println(nm.substring(nm.lastIndexOf('.') + 1) +" quantity: " + cnt.i);
        }
    }
}