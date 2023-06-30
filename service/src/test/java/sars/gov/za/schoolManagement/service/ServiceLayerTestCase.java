/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import sars.gov.za.schoolManagement.common.TestDataSourceConfiguration;
import sars.gov.za.schoolManagement.domain.Employee;
import sars.gov.za.schoolManagement.domain.Grade;
import sars.gov.za.schoolManagement.domain.School;
import sars.gov.za.schoolManagement.domain.Student;
import sars.gov.za.schoolManagement.domain.Subject;
import sars.gov.za.schoolManagement.domain.User;

/**
 *
 * @author S2028398
 */
@EnableJpaAuditing
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDataSourceConfiguration.class)
public class ServiceLayerTestCase {

    @Autowired
    private SchoolServiceLocal schoolService;

    @Autowired
    private GradeServiceLocal gradeService;

    @Autowired
    private SubjectServiceLocal subjectService;
    @Autowired
    private EmployeeServiceLocal employeeService;
    @Autowired
    private StudentServiceLocal studentService;
    @Autowired
    private SystemUserServiceLocal systemUserService;

    public ServiceLayerTestCase() {

    }

    @BeforeClass
    public static void setUpclass() {

    }

    @AfterClass
    public static void tearDownclass() {

    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void TestA() {
        School school = TestCaseHelper.addSchool("SARS Management", "R-1026");
        schoolService.save(school);
    }

    @Test
    public void TestC() {
        School school = schoolService.findBySchoolName("SARS Management");
        Grade grade = TestCaseHelper.addGrade("Grade 12", school);
        gradeService.save(grade);
        Grade grade2 = TestCaseHelper.addGrade("Grade 11", school);
        gradeService.save(grade2);
        Grade grad3 = TestCaseHelper.addGrade("Grade 10", school);
        gradeService.save(grad3);
        Grade grade4 = TestCaseHelper.addGrade("Grade 9", school);
        gradeService.save(grade4);
        Grade grad5 = TestCaseHelper.addGrade("Grade 8", school);
        gradeService.save(grad5);
    }

    @Test
    public void TestD() {
        School school = schoolService.findBySchoolName("SARS Management");
        Subject subject = TestCaseHelper.addSubject("Mathematics", "MAT3520", school);
        subjectService.save(subject);
        Subject subject2 = TestCaseHelper.addSubject("Physical Sciences", "PHY1720", school);
        subjectService.save(subject2);
        Subject subject3 = TestCaseHelper.addSubject("English", "ENG5520", school);
        subjectService.save(subject3);
        Subject subject4 = TestCaseHelper.addSubject("Geography", "GEO2520", school);
        subjectService.save(subject4);
        Subject subject5 = TestCaseHelper.addSubject("Agriculture", "AG1520", school);
        subjectService.save(subject5);
    }

    @Test
    public void TestE() {
        School school = schoolService.findBySchoolName("SARS Management");
        Employee employee = TestCaseHelper.addEmployee("0001", "C1123", school, gradeService.listAll(), subjectService.listAll());
        employeeService.save(employee);
    }

    @Test
    public void TestF() {
        School school = schoolService.findBySchoolName("SARS Management");
        Student student = TestCaseHelper.AddStudent("14014293", gradeService.listAll().get(gradeService.listAll().size() - 3), school, subjectService.listAll());
        studentService.save(student);
    }

    @Test
    public void TestI() {
        School school = schoolService.findBySchoolName("SARS Management");
        User systemUser = TestCaseHelper.addSystemUser("realTerry", "12345", "", "", school);
        systemUserService.save(systemUser);
    }
}
