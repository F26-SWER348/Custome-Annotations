package com.mylecture.exercise7;

import java.lang.reflect.Field;

public class ObjectPrinter {
    public static void print(Object obj){
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder output = new StringBuilder();
        for (Field f: fields){
            if(f.isAnnotationPresent(Display.class)){
                f.setAccessible(true);
                Display dis = f.getAnnotation(Display.class);
                try {
                    output.append(dis.label()).append(": ");
                    if(dis.uppercase())
                        output.append(f.get(obj).toString().toUpperCase() + "\n");
                    else
                        output.append(f.get(obj).toString() + "\n");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(output.toString());
    }

    public static void main(String[] args) {
        Employee employee = new Employee("ahmad", 2500);
        print(employee);
    }
}
