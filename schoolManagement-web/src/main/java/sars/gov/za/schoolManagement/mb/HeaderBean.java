/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.mb;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author S2028398
 */
@ManagedBean
@RequestScoped
public class HeaderBean extends BaseBean{
    @PostConstruct
    public void init() {

    }
    public void logout() {
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) externalContext.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            externalContext.redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(HeaderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
