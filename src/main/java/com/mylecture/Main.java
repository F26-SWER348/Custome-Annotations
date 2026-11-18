package com.mylecture;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        //validateNameField(student, "name");
        validateAllFieldsWithAnnotation(student);
    }

    public static void validateNameField(Student object, String fieldName){
        Class<?> clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if(Modifier.isPrivate(field.getModifiers()))
                field.setAccessible(true);
            String name = (String)field.get(object);
            if(field.isAnnotationPresent(NotNull.class))
                if(name == null)
                    System.out.println(fieldName + " is null");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static void validateAllFieldsWithAnnotation(Object object){
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields){
            if(f.isAnnotationPresent(NotNull.class)){
                f.setAccessible(true);
                try {
                    Object value = f.get(object);
                    if(value == null)
                    {
                        System.out.println(f.getDeclaredAnnotation(NotNull.class).message());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void validateRangeAnnotations(Object object){
        Class<?> clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Range.class)) {
                f.setAccessible(true);
                try {
                    Object value = f.get(object);
                    String type = f.getType().getSimpleName();
                    Range range = f.getDeclaredAnnotation(Range.class);
                    if(type.equals("Double"))
                    {
                        Double valueD = (Double)value;
                        if(valueD < range.min() || valueD > range.max() )
                            System.out.println("Wrong value for "+ f.getName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}