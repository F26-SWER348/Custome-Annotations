package com.mylecture.exercise6;

public class Student {
    @CSVColumn(name = "Student ID", order = 1)
    private int id;

    @CSVColumn(name = "Full Name", order = 2)
    private String name;

    @CSVColumn(name = "GPA", order = 3)
    private double gpa;

    private String password;

    public Student(int id, String name, double gpa, String password) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.password = password;
    }
}
