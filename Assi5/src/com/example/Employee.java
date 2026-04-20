package com.example;

public class Employee {
    private int id;
    private String name;
    private String email;
    private double salary;
    private String department;
    private String phone;

    public Employee(int id, String name, String email, double salary, String department, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public String getPhone() { return phone; }
}
