/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sars.gov.za.schoolManagement.domain.Student;
import sars.gov.za.schoolManagement.persistence.StudentRepository;

/**
 *
 * @author S2028398
 */
@Service
public class StudentService implements StudentServiceLocal {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("The requested id [ " + id + " ] does nor exist."));
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @Override
    public Student deleteById(Long id) {
        Student student = findById(id);
        if (student != null) {
            studentRepository.deleteById(id);
        }
        return student;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public boolean isExist(Student student) {
        return studentRepository.findById(student.getId()) != null;
    }

    @Override
    public Student findUserByStudentNumber(String studentNumber) {
    return studentRepository.findUserByStudentNumber(studentNumber);
    }

}
