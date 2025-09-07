package com.example.employee.Controller;

import com.example.employee.Model.Employee;
import com.example.employee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    public EmployeeService service;

    @RequestMapping("/")
    public String getAllEmployees(Model m){
        m.addAttribute("employees",service.showAllEmployees());
        return "home";
    }

    @GetMapping("/addnew")
    public String addNew(Model m){
        Employee e= new Employee();
        m.addAttribute("employee", e);
        return "new_employee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("employee")Employee e){
        service.saveEmployee(e);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id){
        service.deleteEmployee(id);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateEmployee(@ModelAttribute("id")int id, Model m){
        Employee e=service.updateEmployee(id);
        m.addAttribute("employee",e);
        return "edit_employee";
    }

    @PostMapping("/update")
    public String updateLatest(@ModelAttribute("employee")Employee e)
    {
        service.saveEmployee(e);
        return "redirect:/";
    }
    @GetMapping("/search")
    public String showSearchForm() {
        return "employee-search";
    }
    @GetMapping("/view")
    public String viewEmployee(@RequestParam("name") String name, Model model) {
        List<Employee> employee = service.getEmployeeByName(name);
        System.out.println(name);
        model.addAttribute("employee", employee);
        //System.out.println(employee.toString());
        return "employee-view";
    }

    @GetMapping("/employees/searchBySalGreater")
    public String searchEmployeesBySalaryGreater(@RequestParam(name = "sal", required = false, defaultValue = "0") int sal, Model model) {
        List<Employee> employees = service.findEmployeeBySalGreater(sal);
        model.addAttribute("employees", employees);
        model.addAttribute("sal", sal);
        return "employee-search-greater";  // Thymeleaf template
    }
}
