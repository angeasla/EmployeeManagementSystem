package gr.aueb.cf.employeemanagementsystem.controller;

import gr.aueb.cf.employeemanagementsystem.exceptions.UserNotFoundException;
import gr.aueb.cf.employeemanagementsystem.model.Employee;
import gr.aueb.cf.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired private EmployeeService service;

    @GetMapping("/employees")
    public String showEmployeeList(Model model) {
        List<Employee> listEmployees = service.listAll();
        model.addAttribute("listEmployees", listEmployees);

        return "employees";
    }

    @GetMapping("/employee/new")
    public String showNewForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("pageTitle", "Add new employee");
        return "employee_form";
    }

    @PostMapping("/employee/save")
    public String saveEmployee(Employee employee, RedirectAttributes redirectAttributes) {
        service.save(employee);
        redirectAttributes.addFlashAttribute("message", "The employee has been saved succesfully");
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Employee employee = service.get(id);
            model.addAttribute("employee", employee);
            model.addAttribute("pageTitle", "Edit employee (ID :" + id + ")");
            redirectAttributes.addFlashAttribute("message", "The employee has been updated succesfully");

            return "employee_form";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/employees";
        }

    }

    @GetMapping("/employees/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message", "The employee with ID: " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/employees";
    }
}
