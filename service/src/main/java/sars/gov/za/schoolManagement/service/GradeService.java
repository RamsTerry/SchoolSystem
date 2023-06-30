/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sars.gov.za.schoolManagement.domain.Grade;
import sars.gov.za.schoolManagement.persistence.GradeRepository;

/**
 *
 * @author S2028398
 */
@Service
public class GradeService implements GradeServiceLocal {

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public Grade findById(Long id) {
        return gradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("The requested id [ " + id + " ] does nor exist."));
    }

    @Override
    public Grade update(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public void deleteAll() {
        gradeRepository.deleteAll();
    }

    @Override
    public Grade deleteById(Long id) {
        Grade grade = findById(id);
        if (grade != null) {
            gradeRepository.deleteById(id);
        }
        return grade;
    }

    @Override
    public List<Grade> listAll() {
        return gradeRepository.findAll();
    }

    @Override
    public boolean isExist(Grade grade) {
        return gradeRepository.findById(grade.getId()) != null;
    }


}
