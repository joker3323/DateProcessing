package com.dataprocessingAPP;

public class test {
    public static void main(String[] args) {
        System.out.println(Math.pow(3,0.5));
    }
    private static void pd(double v) {
        if (v <= 1.0) {
            System.out.println("1");
        } else if (v <= 2.0) {
            System.out.println("2");
        } else if (v <= 3.0) {
            System.out.println("3");
        }
        System.out.println("ΩμΔδ±");
        pd(0.1);
        System.out.println(Math.pow(9, 0.5));
        int sum = 12;
        double a = 18.99;
        sum += 12;
        System.out.println(sum);
        System.out.println((float) a);
    }


}
