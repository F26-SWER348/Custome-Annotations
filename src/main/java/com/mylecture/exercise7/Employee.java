package com.mylecture.exercise7;

public class Employee {
    @Display(label = "Employee Name", uppercase = true)
    private String name;
    @Display(label = "Monthly Salary")
    private double salary;
    private String password;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}

