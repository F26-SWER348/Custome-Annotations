package com.mylecture.exercise3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
    public static void invokeTests(){
        CalculatorTests calculatorTests = new CalculatorTests();
        Class<?> clazz = calculatorTests.getClass();
        int passed = 0;
        int failed = 0;
        for (Method method: clazz.getDeclaredMethods()){
            if(method.isAnnotationPresent(MyTest.class)){
                method.setAccessible(true);
                try {
                    method.invoke(calculatorTests);
                    passed++;
                }  catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    failed++;
                }
            }
        }

        System.out.printf("Tests run: %d\n" +
                "Passed: %d\n" +
                "Failed: %d\n", passed + failed, passed, failed);

    }

    public static void main(String[] args) {
        invokeTests();
    }
}
