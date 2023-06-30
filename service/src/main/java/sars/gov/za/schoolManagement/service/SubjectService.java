/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sars.gov.za.schoolManagement.domain.Subject;
import sars.gov.za.schoolManagement.persistence.SubjectRepository;

/**
 *
 * @author S2028398
 */
@Service
public class SubjectService implements SubjectServiceLocal {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("The requested id [ " + id + " ] does nor exist."));
    }

    @Override
    public Subject update(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteAll() {
        subjectRepository.deleteAll();
    }

    @Override
    public Subject deleteById(Long id) {
        Subject subject = findById(id);
        if (subject != null) {
            subjectRepository.deleteById(id);
        }
        return subject;
    }

    @Override
    public List<Subject> listAll() {
        return subjectRepository.findAll();
    }

    @Override
    public boolean isExist(Subject subject) {
        return subjectRepository.findById(subject.getId()) != null;
    }

}
