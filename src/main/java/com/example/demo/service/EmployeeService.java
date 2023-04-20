package com.example.demo.service;

import java.util.List;

public interface EmployeeService {
    void addEmployee(String firstName, String lastName);
    void fireEmployee(String firstName, String lastName);
    void findEmployee(String firstName, String lastName);
    List<Employee> getEmployeeList();
}
