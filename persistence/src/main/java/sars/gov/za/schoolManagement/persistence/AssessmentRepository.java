/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sars.gov.za.schoolManagement.domain.Assessment;

/**
 *
 * @author S2028398
 */
@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    @Query("SELECT e FROM Assessment e WHERE e.student.studentNumber =:studentNumber")
    public List<Assessment> findAssessmentByStudentNumber(@Param("studentNumber") String studentNumber);

    @Query("SELECT e FROM Assessment e WHERE e.educator.employeeId =:employeeId")
    public List<Assessment> findAssessmentByEmployeeId(@Param("employeeId") String employeeId);
}
