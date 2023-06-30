/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.service;

import java.util.List;
import sars.gov.za.schoolManagement.domain.Assessment;

/**
 *
 * @author S2028398
 */
public interface AssessmentServiceLocal {

    Assessment save(Assessment assessment);

    Assessment findById(Long id);

    Assessment update(Assessment assessment);

    void deleteAll();

    Assessment deleteById(Long id);

    List<Assessment> listAll();

    boolean isExist(Assessment assessment);

    List<Assessment> findAssessmentByStudentNumber(String studentNumber);

    List<Assessment> findAssessmentByEmployeeId(String employeeId);
}
