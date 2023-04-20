package com.example.demo.controller;

import com.example.demo.service.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.exceptions.EmployeeAlreadyAddedException;
import com.example.demo.service.exceptions.EmployeeNotFoundException;
import com.example.demo.service.exceptions.EmployeeStorageIsFullException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String printHello() {
        return "<b>Добро пожаловать в сервис по работе с сотрудниками";
    }

    @GetMapping(path = "/add")
    public String addEmp(@RequestParam(value = "firstName", required = false) String firstname, @RequestParam(value = "lastName", required = false) String lastname) {
        try {
            employeeService.addEmployee(firstname, lastname);

            ObjectMapper mapper = new ObjectMapper();
            Employee emp = new Employee(firstname, lastname);

            return mapper.writeValueAsString(emp);

        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            return e.toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/fire")
    public String fireEmp(@RequestParam(value = "firstName", required = false) String firstname, @RequestParam(value = "lastName", required = false) String lastname) {
        try {
            employeeService.fireEmployee(firstname, lastname);

            ObjectMapper mapper = new ObjectMapper();
            Employee emp = new Employee(firstname, lastname);

            return mapper.writeValueAsString(emp);
        } catch (EmployeeNotFoundException e) {
            return e.toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping(path = "/find")
    public String findEmp(@RequestParam(value = "firstName", required = false) String firstname, @RequestParam(value = "lastName", required = false) String lastname) {
        try {
            employeeService.findEmployee(firstname, lastname);

            ObjectMapper mapper = new ObjectMapper();
            Employee emp = new Employee(firstname, lastname);

            return mapper.writeValueAsString(emp);
        } catch (EmployeeNotFoundException e) {
            return e.toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/showall")
    public String getAllEmployes(@RequestParam(value = "pass") String pass){
        if (pass.equals("1111")){
            return employeeService.getEmployeeList().toString();
        }
        return "Не правильный пароль. Доступ ограничен.";
    }
}
