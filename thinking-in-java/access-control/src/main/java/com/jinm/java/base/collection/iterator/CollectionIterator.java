package com.jinm.java.base.collection.iterator;

import java.util.Enumeration;
import java.util.Vector;

public class CollectionIterator {

    public static void main(String[] args) {

        Vector persons = new Vector();
        for (int i = 0; i < 5; i++) {
            persons.addElement(new Person());
        }

        Enumeration e = persons.elements();
        while (e.hasMoreElements()){
            System.out.println(e.nextElement());
        }

    }

}

class Person{

}
