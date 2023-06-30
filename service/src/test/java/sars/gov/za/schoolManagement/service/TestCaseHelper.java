/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.service;

import java.util.Date;
import java.util.List;
import sars.gov.za.schoolManagement.common.AddressType;
import sars.gov.za.schoolManagement.common.EmployeeType;
import sars.gov.za.schoolManagement.common.Gender;
import sars.gov.za.schoolManagement.common.PersonType;
import sars.gov.za.schoolManagement.common.SystemUserStatus;
import sars.gov.za.schoolManagement.domain.Address;
import sars.gov.za.schoolManagement.domain.ContactDetail;
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
public class TestCaseHelper {

    public static School addSchool(String schoolName, String registationNumber) {
        School school = new School();
        school.setCreatedBy("Test");
        school.setCreatedDate(new Date());
        school.setSchoolName(schoolName);
        school.setRegistationNumber(registationNumber);

        ContactDetail contactDetail1 = new ContactDetail();
        contactDetail1.setCreatedBy("Test");
        contactDetail1.setCreatedDate(new Date());
        contactDetail1.setCellphoneNumber("082123456");
        contactDetail1.setTelephoneNumber("012345678");
        contactDetail1.setEmail("sarsmanagement@sars.gov.za");
        school.setContactDetail(contactDetail1);

        Address postalAddress = new Address();
        postalAddress.setCreatedBy("Test");
        postalAddress.setCreatedDate(new Date());
        postalAddress.setAddressType(AddressType.POSTAL);
        postalAddress.setAddressLine1("420");
        postalAddress.setAddressLine2("Sunnyside");
        postalAddress.setArea("Pretoria");
        postalAddress.setCode("0001");
        postalAddress.setStreet("Leyds street");
        school.addAddresses(postalAddress);

        Address resAddress = new Address();
        resAddress.setCreatedBy("Test");
        resAddress.setCreatedDate(new Date());
        resAddress.setAddressType(AddressType.RESIDENTIAL);
        resAddress.setAddressLine1("Aldo Maganu");
        resAddress.setAddressLine2("P-west");
        resAddress.setArea("Pretoria");
        resAddress.setCode("0013");
        resAddress.setStreet("Visagi");
        school.addAddresses(resAddress);
        return school;
    }

    public static Grade addGrade(String designation, School school) {
        Grade grade = new Grade();
        grade.setCreatedBy("Test");
        grade.setCreatedDate(new Date());
        grade.setDesignation(designation);
        grade.setSchool(school);
        return grade;
    }

    public static Subject addSubject(String name, String code, School school) {
        Subject subject = new Subject();
        subject.setCreatedBy("Test");
        subject.setCreatedDate(new Date());
        subject.setName(name);
        subject.setCode(code);
        subject.setSchool(school);
        return subject;
    }

    public static Employee addEmployee(String employeeId, String saceReg, School school, List<Grade> grades, List<Subject> subjects) {
        Employee employee = new Employee();
        employee.setCreatedBy("Test");
        employee.setCreatedDate(new Date());
        employee.setPersonType(PersonType.EMPLOYEE);
        employee.setEmployeeType(EmployeeType.EDUCATOR);
        employee.setEmployeeId(employeeId);
        employee.setSaceReg(saceReg);
        employee.setSchool(school);
        employee.setFirstName("Terry");
        employee.setLastName("Ramurebiwa");
        employee.setIdentityNumber("9887325698723");
        employee.setGender(Gender.MALE);
        employee.setGrades(grades);
        employee.setSubjects(subjects);

        Address address = new Address();
        address.setCreatedBy("Test");
        address.setCreatedDate(new Date());
        address.setAddressType(AddressType.POSTAL);
        address.setAddressLine1("P.O BOX 3540");
        address.setAddressLine2("Thohohoyandou");
        address.setArea("Block D");
        address.setStreet("Muledane");
        address.setCode("0950");
        employee.addAddresses(address);

        Address resAddress = new Address();
        resAddress.setCreatedBy("Test");
        resAddress.setCreatedDate(new Date());
        resAddress.setAddressType(AddressType.RESIDENTIAL);
        resAddress.setAddressLine1("420");
        resAddress.setAddressLine2("Sunnyside");
        resAddress.setArea("Pretoria");
        resAddress.setStreet("Leyds Street");
        resAddress.setCode("0001");
        employee.addAddresses(resAddress);

        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setCreatedBy("Test");
        contactDetail.setCreatedDate(new Date());
        contactDetail.setCellphoneNumber("081123546");
        contactDetail.setTelephoneNumber("012546972");
        contactDetail.setEmail("TRamurebiwa@gmail.com");
        employee.setContactDetail(contactDetail);

        return employee;
    }

    public static Student AddStudent(String studentNumber, Grade grade, School school, List<Subject> subjectsList) {
        Student student = new Student();
        student.setCreatedBy("Test");
        student.setCreatedDate(new Date());
        student.setPersonType(PersonType.LEARNER);
        student.setStudentNumber(studentNumber);
        student.setSchool(school);
        student.setFirstName("Nkhangweleni");
        student.setLastName("Mudau");
        student.setIdentityNumber("9887325698723");
        student.setGender(Gender.MALE);
        student.setGrade(grade);
        student.setSubjectsList(subjectsList);

        Address address = new Address();
        address.setCreatedBy("Test");
        address.setCreatedDate(new Date());
        address.setAddressType(AddressType.POSTAL);
        address.setAddressLine1("P.O BOX 5200");
        address.setAddressLine2("Elim");
        address.setArea("Block D");
        address.setStreet("Thondoni");
        address.setCode("0985");
        student.addAddresses(address);

        Address resAddress = new Address();
        resAddress.setCreatedBy("Test");
        resAddress.setCreatedDate(new Date());
        resAddress.setAddressType(AddressType.RESIDENTIAL);
        resAddress.setAddressLine1("1812");
        resAddress.setAddressLine2("Girth");
        resAddress.setArea("Midrand");
        resAddress.setStreet("Girth Street");
        resAddress.setCode("0012");
        student.addAddresses(resAddress);

        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setCreatedBy("Test");
        contactDetail.setCreatedDate(new Date());
        contactDetail.setCellphoneNumber("023222315");
        contactDetail.setTelephoneNumber("018856487");
        contactDetail.setEmail("NKmudau@gmail.com");
        student.setContactDetail(contactDetail);
        return student;
    }

    public static User addSystemUser(String username, String passwords, String confirmPassword, String changePassword, School school) {
        User systemUser = new User();
        systemUser.setCreatedBy("Test");
        systemUser.setCreatedDate(new Date());
        systemUser.setUsername(username);
        systemUser.setPasswords(passwords);
        systemUser.setConfirmPassword(confirmPassword);
        systemUser.setChangePassword(changePassword);
        systemUser.setFirstName("Terry");
        systemUser.setLastName("Ramurebiwa");
        systemUser.setIdentityNumber("9001352243253");
        systemUser.setIdentifier(systemUser.getIdentityNumber());
        systemUser.setGender(Gender.MALE);
        systemUser.setPersonType(PersonType.ADMINISTRATOR);
        systemUser.setSystemUserStatus(SystemUserStatus.ACTIVE);
        systemUser.setSchool(school);

        Address postalAddress = new Address();
        postalAddress.setCreatedBy("Test");
        postalAddress.setCreatedDate(new Date());
        postalAddress.setAddressType(AddressType.POSTAL);
        postalAddress.setAddressLine1("1558");
        postalAddress.setAddressLine2("Sunnyside");
        postalAddress.setArea("Pretoria");
        postalAddress.setCode("0001");
        postalAddress.setStreet("Acardia street");
        systemUser.addAddresses(postalAddress);

        Address resAddress = new Address();
        resAddress.setCreatedBy("Test");
        resAddress.setCreatedDate(new Date());
        resAddress.setAddressType(AddressType.RESIDENTIAL);
        resAddress.setAddressLine1("Thondoni");
        resAddress.setAddressLine2("Marundu");
        resAddress.setArea("Thohoyandou");
        resAddress.setCode("0985");
        resAddress.setStreet("Hamutsha");
        systemUser.addAddresses(resAddress);

        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setCreatedBy("Test");
        contactDetail.setCreatedDate(new Date());
        contactDetail.setCellphoneNumber("0799035403");
        contactDetail.setTelephoneNumber("0123456789");
        contactDetail.setEmail("TRamurebiwa@sars.gov.za");
        systemUser.setContactDetail(contactDetail);
        return systemUser;
    }

}
