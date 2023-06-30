/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sars.gov.za.schoolManagement.domain.Subject;

/**
 *
 * @author S2028398
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
    
}
