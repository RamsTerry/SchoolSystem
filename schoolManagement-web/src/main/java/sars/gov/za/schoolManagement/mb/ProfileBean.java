/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.mb;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import sars.gov.za.schoolManagement.domain.Address;
import sars.gov.za.schoolManagement.domain.ContactDetail;
import sars.gov.za.schoolManagement.domain.Employee;
import sars.gov.za.schoolManagement.domain.Student;
import sars.gov.za.schoolManagement.domain.User;
import sars.gov.za.schoolManagement.service.EmployeeServiceLocal;
import sars.gov.za.schoolManagement.service.StudentServiceLocal;
import sars.gov.za.schoolManagement.service.SystemUserServiceLocal;

/**
 *
 * @author S2028398
 */
@ManagedBean
@ViewScoped
public class ProfileBean extends BaseBean {

    @Autowired
    private EmployeeServiceLocal employeeService;
    @Autowired
    private StudentServiceLocal studentService;
    @Autowired
    private SystemUserServiceLocal systemUserService;

    private Employee employee;
    private Student student;
    private User user;

    @PostConstruct
    public void init() {
        reset().setList(true);
        setPanelTitleName("Profile");
        employee = employeeService.findUserByEmployeeId(getActiveUser().getIdentifier());
        student = studentService.findUserByStudentNumber(getActiveUser().getIdentifier());
        user = systemUserService.findUserByIdentifier(getActiveUser().getIdentifier());
    }

    public void saveEmployee(Employee employee, User user) {
        if (user.getPasswords().equals(user.getConfirmPassword())) {
            employee.setUpdatedBy(getActiveUser().getIdentifier());
            employee.setUpdatedDate(new Date());

            user.setUpdatedBy(getActiveUser().getIdentifier());
            user.setUpdatedDate(new Date());
            user.setFirstName(employee.getFirstName());
            user.setLastName(employee.getLastName());
            user.setIdentityNumber(employee.getIdentityNumber());
            user.setIdentifier(employee.getEmployeeId());
            user.setGender(employee.getGender());

            for (Address address : employee.getAddresses()) {
                Address postalAddress = new Address();
                postalAddress.setCreatedBy("Test");
                postalAddress.setCreatedDate(new Date());
                postalAddress.setAddressType(address.getAddressType());
                postalAddress.setAddressLine1(address.getAddressLine1());
                postalAddress.setAddressLine2(address.getAddressLine2());
                postalAddress.setArea(address.getArea());
                postalAddress.setCode(address.getCode());
                postalAddress.setStreet(address.getStreet());
                user.addAddresses(postalAddress);
            }
            ContactDetail syscontactDetail = new ContactDetail();
            syscontactDetail.setCreatedBy("Test");
            syscontactDetail.setCreatedDate(new Date());
            syscontactDetail.setCellphoneNumber(syscontactDetail.getCellphoneNumber());
            syscontactDetail.setTelephoneNumber(syscontactDetail.getTelephoneNumber());
            syscontactDetail.setEmail(syscontactDetail.getEmail());
            user.setContactDetail(syscontactDetail);

            addCollection(employee);
            addCollection(user);

        } else {
            addWarningMessage("The password provided is not the same as Confirmed password,please check and re-type");
        }
        addEntity(user);
    }

    public void saveStudent(Student student, User user) {
        if (user.getPasswords().equals(user.getConfirmPassword())) {
            student.setUpdatedBy(getActiveUser().getIdentifier());
            student.setUpdatedDate(new Date());

            user.setUpdatedBy(getActiveUser().getIdentifier());
            user.setUpdatedDate(new Date());
            user.setFirstName(student.getFirstName());
            user.setLastName(student.getLastName());
            user.setIdentityNumber(student.getIdentityNumber());
            user.setIdentifier(student.getStudentNumber());
            user.setGender(student.getGender());

            for (Address address : student.getAddresses()) {
                Address postalAddress = new Address();
                postalAddress.setCreatedBy("Test");
                postalAddress.setCreatedDate(new Date());
                postalAddress.setAddressType(address.getAddressType());
                postalAddress.setAddressLine1(address.getAddressLine1());
                postalAddress.setAddressLine2(address.getAddressLine2());
                postalAddress.setArea(address.getArea());
                postalAddress.setCode(address.getCode());
                postalAddress.setStreet(address.getStreet());
                user.addAddresses(postalAddress);
            }
            ContactDetail syscontactDetail = new ContactDetail();
            syscontactDetail.setCreatedBy("Test");
            syscontactDetail.setCreatedDate(new Date());
            syscontactDetail.setCellphoneNumber(syscontactDetail.getCellphoneNumber());
            syscontactDetail.setTelephoneNumber(syscontactDetail.getTelephoneNumber());
            syscontactDetail.setEmail(syscontactDetail.getEmail());
            user.setContactDetail(syscontactDetail);
            
            addCollection(student);
            addCollection(user);
        } else {
            addWarningMessage("The password provided is not the same as Confirmed password,please check and re-type");
        }
        addEntity(user);
    }

    public void save(Employee employee, User user) {
        if (employee.getId() != null) {
            employeeService.update(employee);
        }
        if (user.getId() != null) {
            systemUserService.update(user);
        }
        addInformationMessage("Profile was successfully updated.");
        reset().setList(true);
        setPanelTitleName("Profile");
    }

    public void cancel(User user) {
        if (user.getId() == null) {
            remove(user);
        }
        reset().setList(true);
        setPanelTitleName("Profile");
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

}
