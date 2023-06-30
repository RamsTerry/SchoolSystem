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
import sars.gov.za.schoolManagement.common.EmployeeType;
import sars.gov.za.schoolManagement.common.Gender;
import sars.gov.za.schoolManagement.common.PersonType;
import sars.gov.za.schoolManagement.domain.Address;
import sars.gov.za.schoolManagement.domain.ContactDetail;
import sars.gov.za.schoolManagement.domain.Employee;
import sars.gov.za.schoolManagement.domain.Grade;
import sars.gov.za.schoolManagement.domain.School;
import sars.gov.za.schoolManagement.domain.Subject;
import sars.gov.za.schoolManagement.service.EmployeeServiceLocal;
import sars.gov.za.schoolManagement.service.GradeServiceLocal;
import sars.gov.za.schoolManagement.service.SchoolServiceLocal;
import sars.gov.za.schoolManagement.service.SubjectServiceLocal;

/**
 *
 * @author S2028398
 */
@ManagedBean
@ViewScoped
public class EmployeeBean extends BaseBean<Employee> {

    @Autowired
    private EmployeeServiceLocal employeeService;
    @Autowired
    private GradeServiceLocal gradeService;
    @Autowired
    private SubjectServiceLocal subjectService;
    @Autowired
    private SchoolServiceLocal schoolService;

    private List<Grade> grades = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();

    private List<AddressType> addressTypes = new ArrayList<>();
    private List<EmployeeType> employeeTypes = new ArrayList<>();
    private List<Gender> genders = new ArrayList<>();
    private List<PersonType> personTypes = new ArrayList<>();

    private Employee staff;
    private School school;
    private Address address;

    private boolean employeeVisible;
    private boolean addressPanel;

    @PostConstruct
    public void init() {
        reset().setList(true);
        setPanelTitleName("Employee");
        addCollections(employeeService.listAll());
        grades = gradeService.listAll();
        subjects = subjectService.listAll();
        addressTypes.addAll(Arrays.asList(AddressType.values()));
        employeeTypes.addAll(Arrays.asList(EmployeeType.values()));
        genders.addAll(Arrays.asList(Gender.values()));
        personTypes.addAll(Arrays.asList(PersonType.values()));
        school = schoolService.findBySchoolName("SARS Management");
        employeeVisible = Boolean.FALSE;
    }

    public void addOrUpdate(Employee employee) {
        reset().setAdd(true);
        if (employee != null) {
            setPanelTitleName("Update Employee");
            if (employee.getEmployeeType().equals(EmployeeType.EDUCATOR) || employee.getEmployeeType().equals(EmployeeType.HOD) || employee.getEmployeeType().equals(EmployeeType.PRINCIPAL)) {
                employeeVisible = Boolean.TRUE;
            } else {
                employeeVisible = Boolean.FALSE;
            }
            employee.setUpdatedBy(getActiveUser().getIdentifier());
            employee.setUpdatedDate(new Date());
        } else {
            employee = new Employee();
            employee.setCreatedBy(getActiveUser().getIdentifier());
            employee.setCreatedDate(new Date());
            employee.setPersonType(PersonType.EMPLOYEE);
            employee.setSchool(school);

            Address physicalAddress = new Address();
            physicalAddress.setAddressType(AddressType.RESIDENTIAL);
            physicalAddress.setCreatedBy(getActiveUser().getIdentifier());
            physicalAddress.setCreatedDate(new Date());
            employee.addAddresses(physicalAddress);

            Address postalAddress = new Address();
            postalAddress.setAddressType(AddressType.POSTAL);
            postalAddress.setCreatedBy(getActiveUser().getIdentifier());
            postalAddress.setCreatedDate(new Date());
            employee.addAddresses(postalAddress);

            ContactDetail contactDetail = new ContactDetail();
            contactDetail.setCreatedBy(getActiveUser().getIdentifier());
            contactDetail.setCreatedDate(new Date());
            employee.setContactDetail(contactDetail);

            addCollection(employee);
        }
        addEntity(employee);
    }

    public void save(Employee employee) {
        if (employee.getId() != null) {
            employeeService.update(employee);
            addInformationMessage("Employee was successfully updated.");
        } else {
            employeeService.save(employee);
            addInformationMessage("Employee successfully created.");
        }
        reset().setList(true);
        setPanelTitleName("Employees");
    }

    public void cancel(Employee employee) {
        if (employee.getId() == null && getEmployees().contains(employee)) {
            remove(employee);
        }
        reset().setList(true);
        setPanelTitleName("Employees");
    }

    public void delete(Employee employee) {
        employeeService.deleteById(employee.getId());
        synchronizeEmployee(employee);
        addInformationMessage("Employee successfully deleted.");
        reset().setList(true);
    }

    public void synchronizeEmployee(Employee employee) {
        if (getCollections().contains(employee)) {
            getCollections().remove(employee);
        }
    }

    public void employeeTypeListener() {
        if (getEntity().getEmployeeType().equals(EmployeeType.EDUCATOR) || getEntity().getEmployeeType().equals(EmployeeType.HOD) || getEntity().getEmployeeType().equals(EmployeeType.PRINCIPAL)) {
            employeeVisible = Boolean.TRUE;
        } else {
            employeeVisible = Boolean.FALSE;
        }
    }

    public void deleteAddress(Address address) {
        if (getEntity().getAddresses().contains(address)) {
            getEntity().getAddresses().remove(address);
        }
    }

    public boolean isEmployeeVisible() {
        return employeeVisible;
    }

    public void setEmployeeVisible(boolean employeeVisible) {
        this.employeeVisible = employeeVisible;
    }

    public List<Employee> getEmployees() {
        return this.getCollections();
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

    public List<EmployeeType> getEmployeeTypes() {
        return employeeTypes;
    }

    public void setEmployeeTypes(List<EmployeeType> employeeTypes) {
        this.employeeTypes = employeeTypes;
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

    public Employee getStaff() {
        return staff;
    }

    public void setStaff(Employee staff) {
        this.staff = staff;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isAddressPanel() {
        return addressPanel;
    }

    public void setAddressPanel(boolean addressPanel) {
        this.addressPanel = addressPanel;
    }

}
