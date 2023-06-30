/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.service;

import java.util.List;
import sars.gov.za.schoolManagement.domain.School;

/**
 *
 * @author S2028398
 */
public interface SchoolServiceLocal {

    School save(School school);

    School findById(Long id);

    School update(School school);

    void deleteAll();

    School deleteById(Long id);

    List<School> listAll();
    
    boolean isExist(School school);
    
    School findBySchoolName(String schoolName);
}
