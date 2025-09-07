package com.example.employee.JpaRepo;

import com.example.employee.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    List<Employee> findEmployeeByName(@Param("name") String name);

    @Query("SELECT e FROM Employee e WHERE e.sal <= :sal")
    List<Employee> findEmployeeBySal(@Param("sal") int sal);

}
