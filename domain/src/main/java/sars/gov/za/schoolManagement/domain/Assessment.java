/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.domain;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.envers.Audited;
import sars.gov.za.schoolManagement.common.AssesmentStatus;

/**
 *
 * @author S2028398
 */
@Audited
@Entity
@Table(name = "assessment")
public class Assessment extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "due_date")
    private Date dueDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "first_submission_date")
    private Date firstSubmissionDate;
    @Column(name = "fullMark")
    private double fullMark;
    @Column(name = "passMark")
    private double passMark;
    @Column(name = "studentMark")
    private double studentMark;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Grade grade;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Subject subject;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Student student;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Employee educator;
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_type")
    private AssesmentStatus assesmentStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getFirstSubmissionDate() {
        return firstSubmissionDate;
    }

    public void setFirstSubmissionDate(Date firstSubmissionDate) {
        this.firstSubmissionDate = firstSubmissionDate;
    }

    public double getFullMark() {
        return fullMark;
    }

    public void setFullMark(double fullMark) {
        this.fullMark = fullMark;
    }

    public double getPassMark() {
        return passMark;
    }

    public void setPassMark(double passMark) {
        this.passMark = passMark;
    }

    public double getStudentMark() {
        return studentMark;
    }

    public void setStudentMark(double studentMark) {
        this.studentMark = studentMark;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Employee getEducator() {
        return educator;
    }

    public void setEducator(Employee educator) {
        this.educator = educator;
    }

    public AssesmentStatus getAssesmentStatus() {
        return assesmentStatus;
    }

    public void setAssesmentStatus(AssesmentStatus assesmentStatus) {
        this.assesmentStatus = assesmentStatus;
    }

}
