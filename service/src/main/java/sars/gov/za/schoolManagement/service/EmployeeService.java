/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sars.gov.za.schoolManagement.domain.Employee;
import sars.gov.za.schoolManagement.persistence.EmployeeRepository;

/**
 *
 * @author S2028398
 */
@Service
public class EmployeeService implements EmployeeServiceLocal {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("The requested id [ " + id + " ] does nor exist."));
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }

    @Override
    public Employee deleteById(Long id) {
      Employee employee = findById(id);
        if (employee != null) {
            employeeRepository.deleteById(id);
        }
        return employee;
    }

    @Override
    public List<Employee> listAll() {
         return employeeRepository.findAll();
    }

    @Override
    public boolean isExist(Employee employee) {
        return employeeRepository.findById(employee.getId()) != null;
    }

    @Override
    public Employee findUserByEmployeeId(String employeeId) {
    return employeeRepository.findUserByEmployeeId(employeeId);
    }

}
