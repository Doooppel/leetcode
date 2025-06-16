package com.doppel.leetcode;

public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 0; i < 10; i++) {
            int number = fibonacci.add(i);
            System.out.println("number = " + number);
        }
    }

    public int add(int count) {
        if (count <=1 ) {
            return count;
        }

        return add(count-1) + add(count - 2);

    }
}
