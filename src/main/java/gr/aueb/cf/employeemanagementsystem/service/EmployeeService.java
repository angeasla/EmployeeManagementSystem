package gr.aueb.cf.employeemanagementsystem.service;

import gr.aueb.cf.employeemanagementsystem.exceptions.UserNotFoundException;
import gr.aueb.cf.employeemanagementsystem.model.Employee;
import gr.aueb.cf.employeemanagementsystem.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired private IEmployeeRepository repo;

    public List<Employee> listAll() {
        return (List<Employee>) repo.findAll();
    }


    public void save(Employee employee) {
        repo.save(employee);
    }

    public Employee get(Integer id) throws UserNotFoundException {
        Optional<Employee> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID: " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any users with ID: " + id);
        }
        repo.deleteById(id);
    }
}
