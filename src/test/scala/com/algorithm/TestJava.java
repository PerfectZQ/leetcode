package com.algorithm;

public class TestJava {
    public static void main(String[] args) {
        int upper = (int) (Math.pow(2, 31) - 1);
        int lower = (int) (Math.pow(2, 31) * -1);
        System.out.println(upper + 1);
        System.out.println(upper);
        System.out.println(lower);
        System.out.println(upper / 10);
        System.out.println(lower / 10);
    }
}
