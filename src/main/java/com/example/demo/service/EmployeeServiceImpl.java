package com.example.demo.service;

import com.example.demo.service.exceptions.EmployeeAlreadyAddedException;
import com.example.demo.service.exceptions.EmployeeNotFoundException;
import com.example.demo.service.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        if (this.employeeList.size() == Integer.MAX_VALUE) {
            throw new EmployeeStorageIsFullException("Коллекция сотрудников переполнена!");
        }
        if (this.employeeList.contains(e)) {
            throw new EmployeeAlreadyAddedException("Пользователь уже имеется в коллекции сотрудников!");
        } else {
            this.employeeList.add(e);
        }
    }

    public void fireEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        if (this.employeeList.contains(e)) {
            this.employeeList.remove(e);
        } else {
            throw new EmployeeNotFoundException("Запрашиваемый для удаления сотрудник не найден!");
        }
    }

    public void findEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        if (this.employeeList.contains(e)){
            System.out.println(e);
        } else {
            throw new EmployeeNotFoundException("Запрашиваемый сотрудник не найден");
        }
    }

}
