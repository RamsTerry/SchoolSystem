/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author S2028398
 */
@ManagedBean
@RequestScoped
public class MenuBean extends BaseBean {

    @PostConstruct
    public void init() {

    }

    public String route(String page) {
        System.out.println("selected page =" + page);
        return defaultRouter(page);
    }

    public String routing(String page) {
        getActiveUser().setModuleWelcomeMessage("Welcome To User");
        return defaultRouter(page);
    }
}
