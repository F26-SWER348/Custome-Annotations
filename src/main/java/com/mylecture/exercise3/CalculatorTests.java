package com.mylecture.exercise3;

public class CalculatorTests {
    @MyTest
    public void testAddition() {
        if (2 + 6 != 5) throw new AssertionError("Addition failed");
    }
    @MyTest
    public void testMultiplication()  {
        if (3 * 5 != 12) throw new AssertionError("Multiplication failed");
    }
    public void helperMethod() {
        System.out.println("Not a test");
    }
}
