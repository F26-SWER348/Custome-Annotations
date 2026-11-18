package com.mylecture;


public class Student {
    @NotNull(message = "Hello, name cannot be null. Go set it to someting.")
    private String name;

    @Range(min = 0.0, max = 4.0)
    private double gpa;

    @Range(min = 17, max = 65)
    private int age;

    private String id;

    @NotNull
    private Teacher advisor;
}
