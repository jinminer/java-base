package com.jinm.deepinjava.assembly.instrument;

import com.sun.tools.attach.VirtualMachine;

public class VirtualMachineDemo {

    public static void main(String[] args) {
        VirtualMachine.list().stream().forEach(System.out::println);
    }
}
