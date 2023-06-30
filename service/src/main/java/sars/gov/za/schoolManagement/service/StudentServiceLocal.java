/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.service;

import java.util.List;
import sars.gov.za.schoolManagement.domain.Student;

/**
 *
 * @author S2028398
 */
public interface StudentServiceLocal {

    Student save(Student student);

    Student findById(Long id);

    Student update(Student student);

    void deleteAll();

    Student deleteById(Long id);

    List<Student> listAll();

    boolean isExist(Student student);
    Student findUserByStudentNumber(String studentNumber);
}
