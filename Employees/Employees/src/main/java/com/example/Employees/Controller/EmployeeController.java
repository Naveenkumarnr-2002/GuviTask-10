package com.example.Employees.Controller;

import com.example.Employees.Entity.Employee;
import com.example.Employees.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        repository.save(employee);
        return "redirect:/displayAll";
    }

    @GetMapping("/displayAll")
    public String displayAll(Model model) {
        List<Employee> employees = repository.findAll();
        model.addAttribute("employees", employees);
        return "displayAll";
    }

    @GetMapping("/display/{id}")
    public String displayById(@PathVariable String id, Model model) {
        Employee emp = repository.findById(id).orElse(null);
        model.addAttribute("employee", emp);
        return "displayOne";
    }
}

