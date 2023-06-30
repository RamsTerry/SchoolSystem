/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.mb;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sars.gov.za.schoolManagement.common.EmployeeType;
import sars.gov.za.schoolManagement.common.PersonType;
import sars.gov.za.schoolManagement.common.SystemUserStatus;
import sars.gov.za.schoolManagement.common.SystemUserType;
import sars.gov.za.schoolManagement.domain.User;

/**
 *
 * @author S2028398
 */
@ManagedBean
@SessionScoped
public class ActiveUserBean implements Serializable {

    private String username;
    private boolean userLoginIndicator;
    private PersonType personType;
    private SystemUserStatus systemUserStatus;
    private String moduleWelcomeMessage;
    private String loggedOnUserFullName;
    private String fistName;
    private String lastName;
    private String identifier;
    private Router router = new Router();

    private boolean learner;
    private boolean educator;
    private boolean principal;
    private boolean headOfDepartment;
    private boolean generalWorker;
    private boolean administrator;

    public ActiveUserBean() {
        userLoginIndicator = Boolean.FALSE;
    }

    public void setLogonUserSession(User user) {
        if (user.getId() != null) {
            this.setUsername(user.getUsername());
            this.setLoggedOnUserFullName(user.getUsername());
            this.setFistName(user.getFirstName());
            this.setLastName(user.getLastName());
            this.setPersonType(user.getPersonType());
            this.setSystemUserStatus(user.getSystemUserStatus());
            this.setIdentifier(user.getIdentifier());
            this.setUserLoginIndicator(true);
        }
    }
    
     public ActiveUserBean reset() {
         setEducator(false);
         setGeneralWorker(false);
         setHeadOfDepartment(false);
         setPrincipal(false);
         setAdministrator(false);
         setLearner(false);
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isUserLoginIndicator() {
        return userLoginIndicator;
    }

    public void setUserLoginIndicator(boolean userLoginIndicator) {
        this.userLoginIndicator = userLoginIndicator;
    }

    public String getModuleWelcomeMessage() {
        return moduleWelcomeMessage;
    }

    public void setModuleWelcomeMessage(String moduleWelcomeMessage) {
        this.moduleWelcomeMessage = moduleWelcomeMessage;
    }

    public String getLoggedOnUserFullName() {
        return loggedOnUserFullName;
    }

    public void setLoggedOnUserFullName(String loggedOnUserFullName) {
        this.loggedOnUserFullName = loggedOnUserFullName;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SystemUserStatus getSystemUserStatus() {
        return systemUserStatus;
    }

    public void setSystemUserStatus(SystemUserStatus systemUserStatus) {
        this.systemUserStatus = systemUserStatus;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public boolean isLearner() {
        return learner;
    }

    public void setLearner(boolean learner) {
        this.learner = learner;
    }

    public boolean isEducator() {
        return educator;
    }

    public void setEducator(boolean educator) {
        this.educator = educator;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public boolean isHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(boolean headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public boolean isGeneralWorker() {
        return generalWorker;
    }

    public void setGeneralWorker(boolean generalWorker) {
        this.generalWorker = generalWorker;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

}
