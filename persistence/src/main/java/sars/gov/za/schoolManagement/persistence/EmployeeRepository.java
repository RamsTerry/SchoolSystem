/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sars.gov.za.schoolManagement.domain.Employee;

/**
 *
 * @author S2028398
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findUserByEmployeeId(String employeeId);
}
