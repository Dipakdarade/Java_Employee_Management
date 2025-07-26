package com.example.Employee.Controller;

import com.example.Employee.Entity.EmployeeEntity;
import com.example.Employee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Add New Employee *****************************************
    @PostMapping("/hire")
    public Object addEmp(@RequestBody EmployeeEntity employee) {
        return employeeService.addEmployee(employee);
    }

    // find Employee By Id ****************************************
    @GetMapping("/find/{name}")
    public Object getByName(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    // Update Employee Details *************************************
    @PutMapping("/update/{name}")
    public Object updateDetails(@PathVariable String name, @RequestBody EmployeeEntity employee) {
        return employeeService.updateInfo(name, employee);
    }

    //   Delete Employee ********************************************
    @DeleteMapping("/fire/{name}")
    public Object deleteEmp(@PathVariable String name) {
        return employeeService.deleteData(name);
    }

    // Get All Employees *********************************************
    @GetMapping("/findAll")
    public Object getAll() {
        return employeeService.getAllEmp();
    }
    @DeleteMapping("/deleteAll")
    public String delete(){
        return employeeService.delete();
    }
}
