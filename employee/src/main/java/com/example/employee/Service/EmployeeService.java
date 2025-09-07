package com.example.employee.Service;

import com.example.employee.JpaRepo.EmployeeRepo;
import com.example.employee.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeRepo repo;

    public List<Employee> showAllEmployees(){
        return repo.findAll();
    }

    public void saveEmployee(Employee e){
        repo.save(e);
    }

    public void deleteEmployee(int id){
        repo.deleteById(id);
    }

    public Employee updateEmployee(int id){
       return  repo.findById(id).orElse(new Employee());
    }

    public List<Employee> getEmployeeByName(String name) {
        return repo.findEmployeeByName(name);
    }

    public List<Employee> findEmployeeBySalGreater(int sal){
        return repo.findEmployeeBySal(sal);
    }
}
