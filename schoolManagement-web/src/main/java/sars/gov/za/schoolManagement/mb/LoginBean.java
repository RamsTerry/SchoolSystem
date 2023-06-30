/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.mb;

import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;
import sars.gov.za.schoolManagement.common.PersonType;
import sars.gov.za.schoolManagement.common.SystemUserStatus;
import sars.gov.za.schoolManagement.common.SystemUserType;
import sars.gov.za.schoolManagement.domain.User;
import sars.gov.za.schoolManagement.service.SystemUserServiceLocal;

/**
 *
 * @author S2028398
 */
@ManagedBean
@RequestScope
public class LoginBean extends BaseBean {

    @Autowired
    private SystemUserServiceLocal systemUserService;

    private String username;
    private String password;

    public void login() {
        User user = systemUserService.findUserByUsernameAndPasswords(username, password);
        if (user != null) {
            if (user.getPersonType().equals(PersonType.ADMINISTRATOR) && user.getSystemUserStatus().equals(SystemUserStatus.ACTIVE)) {
                getActiveUser().setModuleWelcomeMessage("Welcome To Administrator Console");
                getActiveUser().getRouter().reset().setAdministrator(true);
                getActiveUser().setLogonUserSession(user);
                redirect("landingPage");
            } else if (user.getPersonType().equals(PersonType.EMPLOYEE) && user.getSystemUserStatus().equals(SystemUserStatus.ACTIVE)) {
                getActiveUser().setModuleWelcomeMessage("Welcome To Employee Console");
                getActiveUser().getRouter().reset().setEducator(true);
                getActiveUser().getRouter().reset().setHod(true);
                getActiveUser().getRouter().reset().setPrincipal(true);
                getActiveUser().setLogonUserSession(user);
                redirect("landingPage");
            }else if (user.getPersonType().equals(PersonType.LEARNER) &&user.getSystemUserStatus().equals(SystemUserStatus.ACTIVE)) {
                getActiveUser().setModuleWelcomeMessage("Welcome To Student Console");
                getActiveUser().getRouter().reset().setLearner(true);
                getActiveUser().setLogonUserSession(user);
                redirect("landingPage");
            }
            else {
                redirect("login");
            }
        } else {
            addErrorMessage("The user with the username " + username + " is not Authorised to use the system");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
