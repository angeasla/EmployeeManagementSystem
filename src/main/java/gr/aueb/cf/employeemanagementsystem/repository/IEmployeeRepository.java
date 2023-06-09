package gr.aueb.cf.employeemanagementsystem.repository;

import gr.aueb.cf.employeemanagementsystem.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeRepository extends CrudRepository<Employee, Integer> {
    public Long countById(Integer id);
}
