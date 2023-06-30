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
import sars.gov.za.schoolManagement.common.SystemUserStatus;
import sars.gov.za.schoolManagement.domain.Employee;
import sars.gov.za.schoolManagement.domain.School;
import sars.gov.za.schoolManagement.domain.Student;
import sars.gov.za.schoolManagement.domain.User;
import sars.gov.za.schoolManagement.service.EmployeeServiceLocal;
import sars.gov.za.schoolManagement.service.SchoolServiceLocal;
import sars.gov.za.schoolManagement.service.StudentServiceLocal;
import sars.gov.za.schoolManagement.service.SystemUserServiceLocal;

/**
 *
 * @author S2028398
 */
@ManagedBean
@ViewScoped
public class UserBean extends BaseBean<User> {

    @Autowired
    private EmployeeServiceLocal employeeService;
    @Autowired
    private StudentServiceLocal studentService;
    @Autowired
    private SchoolServiceLocal schoolService;
    @Autowired
    private SystemUserServiceLocal systemUserService;

    private List<Employee> employees = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    private List<AddressType> addressTypes = new ArrayList<>();
    private List<Gender> genders = new ArrayList<>();
    private List<SystemUserStatus> systemUserStatuses = new ArrayList<>();
    private List<PersonType> personTypes = new ArrayList<>();

    private Employee employee;
    private Student student;
    private User user;
    private School school;
    private boolean employeeVisible;
    private boolean studentVisible;

    @PostConstruct
    public void init() {
        reset().setList(true);
        setPanelTitleName("Users");
        addCollections(systemUserService.listAll());
        employees = employeeService.listAll();
        students = studentService.listAll();
        addressTypes.addAll(Arrays.asList(AddressType.values()));
        genders.addAll(Arrays.asList(Gender.values()));
        systemUserStatuses.addAll(Arrays.asList(SystemUserStatus.values()));
        personTypes.addAll(Arrays.asList(PersonType.values()));
        school = schoolService.findBySchoolName("SARS Management");
    }

    public void addOrUpdate(User user) {
        reset().setAdd(true);
        if (user != null) {
            setPanelTitleName("Update User");
            if (user.getPersonType().equals(PersonType.ADMINISTRATOR)) {
                employee = employeeService.findUserByEmployeeId(user.getIdentifier());
            } else if (user.getPersonType().equals(PersonType.EMPLOYEE)) {
                employee = employeeService.findUserByEmployeeId(user.getIdentifier());
            } else if (user.getPersonType().equals(PersonType.LEARNER)) {
                student = studentService.findUserByStudentNumber(user.getIdentifier());
            }
            user.setUpdatedBy(getActiveUser().getIdentifier());
            user.setUpdatedDate(new Date());
        } else {
            user = new User();
            user.setCreatedBy(getActiveUser().getIdentifier());
            user.setCreatedDate(new Date());
            user.setSchool(school);
            addCollection(user);
        }
        addEntity(user);
    }

    public void save(User user) {
        if (user.getPasswords().equals(user.getConfirmPassword())) {
            if (user.getId() != null) {
                systemUserService.update(user);
                addInformationMessage("User was successfully updated.");
            } else {
                if (user.getPersonType().equals(PersonType.ADMINISTRATOR) || user.getPersonType().equals(PersonType.EMPLOYEE)) {
               systemUserService.save(user);
                addInformationMessage("User successfully created.");
            } else if (user.getPersonType().equals(PersonType.LEARNER)) {
                    systemUserService.save(user);
                addInformationMessage("User successfully created.");
            }
                
            }
            reset().setList(true);
            setPanelTitleName("Users");
        }
    }

    public void cancel(User user) {
        if (user.getId() == null && getUsers().contains(user)) {
            remove(user);
        }
        reset().setList(true);
        setPanelTitleName("Users");
    }

    public void delete(User user) {
        systemUserService.deleteById(user.getId());
        remove(user);
        addInformationMessage("User was successfully deleted");
        reset().setList(true);
        setPanelTitleName("Users");
    }

    public List<String> getIdentifiers() {
        List<String> allIdentifiers = new ArrayList<>();
        for (User user : systemUserService.listAll()) {
            allIdentifiers.add(user.getIdentifier());
        }
        return allIdentifiers;
    }

    public void systemUserListerner() {
        if (getEntity().getPersonType().equals(PersonType.EMPLOYEE) || getEntity().getPersonType().equals(PersonType.ADMINISTRATOR)) {
            employeeVisible = Boolean.TRUE;
            studentVisible = Boolean.FALSE;
        } else if (getEntity().getPersonType().equals(PersonType.LEARNER)) {
            studentVisible = Boolean.TRUE;
            employeeVisible = Boolean.FALSE;
        }
    }

    public List<User> getUsers() {
        return this.getCollections();
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

    public List<SystemUserStatus> getSystemUserStatuses() {
        return systemUserStatuses;
    }

    public void setSystemUserStatuses(List<SystemUserStatus> systemUserStatuses) {
        this.systemUserStatuses = systemUserStatuses;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<PersonType> getPersonTypes() {
        return personTypes;
    }

    public void setPersonTypes(List<PersonType> personTypes) {
        this.personTypes = personTypes;
    }

    public boolean isEmployeeVisible() {
        return employeeVisible;
    }

    public void setEmployeeVisible(boolean employeeVisible) {
        this.employeeVisible = employeeVisible;
    }

    public boolean isStudentVisible() {
        return studentVisible;
    }

    public void setStudentVisible(boolean studentVisible) {
        this.studentVisible = studentVisible;
    }

}
