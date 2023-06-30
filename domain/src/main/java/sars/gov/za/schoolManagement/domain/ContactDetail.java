/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

/**
 *
 * @author S2028398
 */
@Audited
@Entity
@Table(name = "contact_detail")
public class ContactDetail extends BaseEntity {

    @Column(name = "cellphone_number")
    private String cellphoneNumber;
    @Column(name = "telephone_number")
    private String telephoneNumber;
    @Column(name = "email_address")
    private String email;

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
