/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.mb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import sars.gov.za.schoolManagement.common.AddressType;
import sars.gov.za.schoolManagement.common.Gender;
import sars.gov.za.schoolManagement.common.PersonType;
import sars.gov.za.schoolManagement.domain.Address;
import sars.gov.za.schoolManagement.domain.ContactDetail;
import sars.gov.za.schoolManagement.domain.Grade;
import sars.gov.za.schoolManagement.domain.School;
import sars.gov.za.schoolManagement.domain.Student;
import sars.gov.za.schoolManagement.domain.Subject;
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
public class StudentBean extends BaseBean<Student> {

    @Autowired
    private StudentServiceLocal studentService;
    @Autowired
    private GradeServiceLocal gradeService;
    @Autowired
    private SubjectServiceLocal subjectService;
    @Autowired
    private SchoolServiceLocal schoolService;

    private List<Grade> grades = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();

    private List<AddressType> addressTypes = new ArrayList<>();
    private List<Gender> genders = new ArrayList<>();
    private List<PersonType> personTypes = new ArrayList<>();

    private Student student;
    private School school;
    private Address address;
    private AddressType addressType;

    private boolean addressPanel;

    @PostConstruct
    public void init() {
        reset().setList(true);
        setPanelTitleName("Student");
        addCollections(studentService.listAll());
        grades = gradeService.listAll();
        subjects = subjectService.listAll();
        addressTypes = Arrays.asList(AddressType.values());
        genders = Arrays.asList(Gender.values());
        school = schoolService.findBySchoolName("SARS Management");
    }

    public void addOrUpdate(Student student) {
        reset().setAdd(true);
        if (student != null) {
            student.setUpdatedBy(getActiveUser().getIdentifier());
            student.setUpdatedDate(new Date());
        } else {
            student = new Student();
            student.setCreatedBy(getActiveUser().getIdentifier());
            student.setCreatedDate(new Date());
            student.setPersonType(PersonType.LEARNER);
            student.setSchool(school);

            Address physicalAddress = new Address();
            physicalAddress.setAddressType(AddressType.RESIDENTIAL);
            physicalAddress.setCreatedBy(getActiveUser().getIdentifier());
            physicalAddress.setCreatedDate(new Date());
            student.addAddresses(physicalAddress);

            Address postalAddress = new Address();
            postalAddress.setAddressType(AddressType.POSTAL);
            postalAddress.setCreatedBy(getActiveUser().getIdentifier());
            postalAddress.setCreatedDate(new Date());
            student.addAddresses(postalAddress);

            ContactDetail contactDetail = new ContactDetail();
            contactDetail.setCreatedBy(getActiveUser().getIdentifier());
            contactDetail.setCreatedDate(new Date());
            student.setContactDetail(contactDetail);

            addCollection(student);
        }
        addEntity(student);
    }

    public void save(Student student) {
        if (student.getId() != null) {
            studentService.update(student);
            addInformationMessage("Student was successfully updated.");
        } else {
            studentService.save(student);
            addInformationMessage("Student successfully created.");
        }
        reset().setList(true);
        setPanelTitleName("Students");
    }

    public void cancel(Student student) {
        if (student.getId() == null && getStudents().contains(student)) {
            remove(student);
        }
        reset().setList(true);
        setPanelTitleName("Students");
    }

    public void delete(Student student) {
        studentService.deleteById(student.getId());
        synchronizeEmployee(student);
        addInformationMessage("Student successfully deleted.");
        reset().setList(true);
    }

    public void synchronizeEmployee(Student student) {
        if (getCollections().contains(student)) {
            getCollections().remove(student);
        }
    }

    public void deleteAddress(Address address) {
        if (getEntity().getAddresses().contains(address)) {
            getEntity().getAddresses().remove(address);
        }
    }

    public List<Student> getStudents() {
        return this.getCollections();
    }

    public StudentServiceLocal getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentServiceLocal studentService) {
        this.studentService = studentService;
    }

    public GradeServiceLocal getGradeService() {
        return gradeService;
    }

    public void setGradeService(GradeServiceLocal gradeService) {
        this.gradeService = gradeService;
    }

    public SubjectServiceLocal getSubjectService() {
        return subjectService;
    }

    public void setSubjectService(SubjectServiceLocal subjectService) {
        this.subjectService = subjectService;
    }

    public SchoolServiceLocal getSchoolService() {
        return schoolService;
    }

    public void setSchoolService(SchoolServiceLocal schoolService) {
        this.schoolService = schoolService;
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

    public List<AddressType> getAddressTypes() {
        return addressTypes;
    }

    public void setAddressTypes(List<AddressType> addressTypes) {
        this.addressTypes = addressTypes;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    public List<PersonType> getPersonTypes() {
        return personTypes;
    }

    public void setPersonTypes(List<PersonType> personTypes) {
        this.personTypes = personTypes;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public boolean isAddressPanel() {
        return addressPanel;
    }

    public void setAddressPanel(boolean addressPanel) {
        this.addressPanel = addressPanel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
