/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.mb;

import java.io.Serializable;

/**
 *
 * @author S2028398
 */
public class Router implements Serializable {

    private boolean administrator;
    private boolean educator;
    private boolean principal;
    private boolean hod;
    private boolean learner;

    public Router reset() {
        administrator = Boolean.FALSE;
        educator = Boolean.FALSE;
        principal = Boolean.FALSE;
        hod = Boolean.FALSE;
        learner = Boolean.FALSE;
        return this;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
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

    public boolean isHod() {
        return hod;
    }

    public void setHod(boolean hod) {
        this.hod = hod;
    }

    public boolean isLearner() {
        return learner;
    }

    public void setLearner(boolean learner) {
        this.learner = learner;
    }

}
