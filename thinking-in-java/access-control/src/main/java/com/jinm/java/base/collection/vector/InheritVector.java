package com.jinm.java.base.collection.vector;

import java.util.Vector;

public class InheritVector extends Vector {

    @Override
    public synchronized void addElement(Object obj) {
        super.addElement(obj);
    }
}
