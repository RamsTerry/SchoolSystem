/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sars.gov.za.schoolManagement.domain.Assessment;
import sars.gov.za.schoolManagement.persistence.AssessmentRepository;

/**
 *
 * @author S2028398
 */
@Service
public class AssessmentService implements AssessmentServiceLocal {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Override
    public Assessment save(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    @Override
    public Assessment findById(Long id) {
        return assessmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("The requested id [ " + id + " ] does nor exist."));
    }

    @Override
    public Assessment update(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    @Override
    public void deleteAll() {
        assessmentRepository.deleteAll();
    }

    @Override
    public Assessment deleteById(Long id) {
        Assessment assessment = findById(id);
        if (assessment != null) {
            assessmentRepository.deleteById(id);
        }
        return assessment;
    }

    @Override
    public List<Assessment> listAll() {
        return assessmentRepository.findAll();
    }

    @Override
    public boolean isExist(Assessment assessment) {
        return assessmentRepository.findById(assessment.getId()) != null;
    }

    @Override
    public List<Assessment> findAssessmentByStudentNumber(String studentNumber) {
        return assessmentRepository.findAssessmentByStudentNumber(studentNumber);
    }

    @Override
    public List<Assessment> findAssessmentByEmployeeId(String employeeId) {
        return assessmentRepository.findAssessmentByEmployeeId(employeeId);
    }

}
