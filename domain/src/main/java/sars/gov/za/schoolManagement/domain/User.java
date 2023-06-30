/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.envers.Audited;
import sars.gov.za.schoolManagement.common.SystemUserStatus;

/**
 *
 * @author S2028398
 */
@Audited
@Entity
@Table(name = "sys_user")
public class User extends Person {

    @Column(name = "username")
    private String username;
    @Column(name = "identifier", unique = true)
    private String identifier;
    @Column(name = "password")
    private String passwords;
    @Column(name = "confirm_password")
    private String confirmPassword;
    @Column(name = "change_password")
    private String changePassword;
    @Enumerated(EnumType.STRING)
    @Column(name = "system_user_status")
    private SystemUserStatus systemUserStatus;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private School school;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(String changePassword) {
        this.changePassword = changePassword;
    }

    public SystemUserStatus getSystemUserStatus() {
        return systemUserStatus;
    }

    public void setSystemUserStatus(SystemUserStatus systemUserStatus) {
        this.systemUserStatus = systemUserStatus;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

}
