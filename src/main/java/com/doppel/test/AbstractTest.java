package com.doppel.test;

public class AbstractTest {
    public static void main(String[] args) {
        AbstractA a = new ChildA();
        System.out.println("a.testSuper() = " + a.testSuper());
    }
}
