/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import sars.gov.za.schoolManagement.domain.Employee;
import sars.gov.za.schoolManagement.domain.Grade;
import sars.gov.za.schoolManagement.domain.School;
import sars.gov.za.schoolManagement.domain.Student;
import sars.gov.za.schoolManagement.domain.Subject;
import sars.gov.za.schoolManagement.service.EmployeeServiceLocal;
import sars.gov.za.schoolManagement.service.GradeServiceLocal;
import sars.gov.za.schoolManagement.service.SchoolServiceLocal;
import sars.gov.za.schoolManagement.service.StudentServiceLocal;
import sars.gov.za.schoolManagement.service.SubjectServiceLocal;

/**
 *
 * @author S2028398
 */
@ManagedBean
@ViewScoped
public class SchoolBean extends BaseBean<School> {

    @Autowired
    private SchoolServiceLocal schoolService;
    @Autowired
    private SubjectServiceLocal subjectService;
    @Autowired
    private GradeServiceLocal gradeService;
    @Autowired
    private EmployeeServiceLocal employeeService;
    @Autowired
    private StudentServiceLocal studentService;

    private List<Subject> subjects = new ArrayList<>();
    private List<Grade> grades = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    private School school;

    @PostConstruct
    public void init() {
        reset().setList(true);
        setPanelTitleName("School");
        school = schoolService.findBySchoolName("SARS Management");
        grades = gradeService.listAll();
        subjects = subjectService.listAll();
        employees = employeeService.listAll();
        students = studentService.listAll();
    }

    public void update(School school) {
        reset().setAdd(true);
        if (school != null) {
            setPanelTitleName("Update School");
            school.setUpdatedBy(getActiveUser().getIdentifier());
            school.setUpdatedDate(new Date());
        }
        addEntity(school);
    }

    public void save(School school) {
        if (school.getId() != null) {
            schoolService.update(school);
            addInformationMessage("School was successfully updated.");
        }
        reset().setList(true);
        setPanelTitleName("School");
    }

    public void cancel(School school) {
        if (school.getId() == null && getSchools().contains(school)) {
            remove(school);
        }
        reset().setList(true);
        setPanelTitleName("School");
    }

    public List<School> getSchools() {
        return this.getCollections();
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
