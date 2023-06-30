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
import sars.gov.za.schoolManagement.common.AssesmentStatus;
import sars.gov.za.schoolManagement.domain.Assessment;
import sars.gov.za.schoolManagement.domain.Employee;
import sars.gov.za.schoolManagement.domain.Grade;
import sars.gov.za.schoolManagement.domain.Student;
import sars.gov.za.schoolManagement.domain.Subject;
import sars.gov.za.schoolManagement.service.AssessmentServiceLocal;
import sars.gov.za.schoolManagement.service.EmployeeServiceLocal;
import sars.gov.za.schoolManagement.service.GradeServiceLocal;
import sars.gov.za.schoolManagement.service.StudentServiceLocal;
import sars.gov.za.schoolManagement.service.SubjectServiceLocal;

/**
 *
 * @author S2028398
 */
@ManagedBean
@ViewScoped
public class AssessmentBean extends BaseBean<Assessment> {

    @Autowired
    private AssessmentServiceLocal assessmentService;
    @Autowired
    private EmployeeServiceLocal employeeService;
    @Autowired
    private StudentServiceLocal studentService;
    @Autowired
    private GradeServiceLocal gradeService;
    @Autowired
    private SubjectServiceLocal subjectService;

    private List<Student> students = new ArrayList<>();
    private List<Grade> grades = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();

    private Assessment assessment;
    private Employee educator;
    private Student student;

    private boolean visible;

    @PostConstruct
    public void init() {
        reset().setList(true);
        addCollections(assessmentService.listAll());
        grades = gradeService.listAll();
        subjects = subjectService.listAll();
        if (getActiveUser().isLearner()) {
            student = studentService.findUserByStudentNumber(getActiveUser().getIdentifier());
        } else {
            educator = employeeService.findUserByEmployeeId(getActiveUser().getIdentifier());
        }
        visible = Boolean.FALSE;
    }

    public void addOrUpdate(Assessment assessment) {
        reset().setAdd(true);
        if (assessment != null) {
            assessment.setUpdatedBy(getActiveUser().getIdentifier());
            assessment.setUpdatedDate(new Date());

        } else {
            assessment = new Assessment();
            assessment.setCreatedBy(getActiveUser().getIdentifier());
            assessment.setCreatedDate(new Date());
            assessment.setEducator(educator);

            addCollection(assessment);
        }
        addEntity(assessment);
    }

    public void save(Assessment assessment) {
        if (assessment.getStudentMark() > assessment.getPassMark()) {
            assessment.setAssesmentStatus(AssesmentStatus.PASSED);
        } else if (assessment.getStudentMark() < assessment.getPassMark()) {
            assessment.setAssesmentStatus(AssesmentStatus.FAILED);
        } else if (assessment.getStudentMark() == assessment.getPassMark()) {
            assessment.setAssesmentStatus(AssesmentStatus.CONDONED);
        }
        if (assessment.getId() != null) {
            assessmentService.update(assessment);
            addInformationMessage("Assessment Successfully updated");
        } else {
            assessmentService.save(assessment);
            addInformationMessage("Assessment Successfully added");
        }
        reset().setList(true);
    }

    public void delete(Assessment assessment) {
        assessmentService.deleteById(assessment.getId());
        synchronizeAssessment(assessment);
        reset().setList(true);
    }

    public void synchronizeAssessment(Assessment assessment) {
        if (getCollections().contains(assessment)) {
            getCollections().remove(assessment);
        }
    }

    public void cancel(Assessment assessment) {
        if (assessment.getId() == null && getAssessments().contains(assessment)) {
            remove(assessment);
        }
        reset().setList(true);
    }

    public void gradeSelectionListener() {
        students.clear();
        for (Student stud : studentService.listAll()) {
            if (stud.getGrade().getDesignation().equals(getEntity().getGrade().getDesignation())) {
                students.add(stud);
            }
        }
        visible = Boolean.TRUE;
//        reset().setVisible(true);
    }

    public List<Assessment> getAssessments() {
        return this.getCollections();
    }

    public EmployeeServiceLocal getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeServiceLocal employeeService) {
        this.employeeService = employeeService;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public Employee getEducator() {
        return educator;
    }

    public void setEducator(Employee educator) {
        this.educator = educator;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

}
