package com.example.Employee.Service;
import com.example.Employee.Entity.EmployeeEntity;
import com.example.Employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    public ResponseEntity<Object> addEmployee(EmployeeEntity employee) {
        if (employee.getName().isEmpty() || employee.getAddress().isEmpty() || employee.getSalary() <= 0 || employee.getJoiningDate().isAfter(LocalDate.now()) || employee.getOrganization().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Data Entered, Please try again");
        } else {
            employeeRepo.save(employee);
            return ResponseEntity.ok(employee.getName() + " Joined Organization on :- " + employee.getJoiningDate());
        }
    }
    public ResponseEntity<Object> findByName(String name) {
        EmployeeEntity employee = employeeRepo.findByName(name).orElse(null);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(name + "  Not Exist");
        }
    }
    public ResponseEntity<String> updateInfo(String name, EmployeeEntity employee) {
        if (employee == null) {
            return ResponseEntity.badRequest().body("Employee data is missing!");
        }
        EmployeeEntity employee1 = employeeRepo.findByName(name).orElse(null);
        if (employee1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Exist!");
        }
        // Validate employee details
        if (employee.getName() == null || employee.getName().isEmpty() ||
                employee.getAddress() == null || employee.getAddress().isEmpty() ||
                employee.getSalary() <= 0 ||
                employee.getJoiningDate() == null || employee.getJoiningDate().isAfter(LocalDate.now()) ||
                employee.getOrganization() == null || employee.getOrganization().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Data Entered, Please try again");
        }
        // Update Employee details
        employee1.setName(employee.getName());
        employee1.setAddress(employee.getAddress());
        employee1.setSalary(employee.getSalary());
        employee1.setJoiningDate(employee.getJoiningDate());

        employeeRepo.save(employee1);
        return ResponseEntity.ok(employee.getName() + " Details Updated! ");
    }
    @Transactional
    public ResponseEntity<String> deleteData(String name) {
        EmployeeEntity emp = employeeRepo.findByName(name).orElse(null);
        if (emp != null) {
            employeeRepo.deleteByName(name);
            return ResponseEntity.ok(emp.getName() + " Fired on:- " + LocalDate.now());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Exist");
        }
    }
    public ResponseEntity<Object> getAllEmp() {
        if (!employeeRepo.findAll().isEmpty()) {
            return ResponseEntity.ok(employeeRepo.findAll());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Database is Empty!");
        }
    }
        @Transactional
                public String delete(){
            employeeRepo.deleteAll();
        return "Deleted All Entries";
    }
}


