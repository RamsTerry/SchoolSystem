/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

/**
 *
 * @author S2028398
 */
@Audited
@Entity
@Table(name = "school")
public class School extends BaseEntity {

    @Column(name = "school_name")
    private String schoolName;
    @Column(name = "registration_number")
    private String registationNumber;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ContactDetail contactDetail;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "school_id")
    private List<Address> addresses = new ArrayList<>();

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getRegistationNumber() {
        return registationNumber;
    }

    public void setRegistationNumber(String registationNumber) {
        this.registationNumber = registationNumber;
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddresses(Address address) {
        this.addresses.add(address);
    }

    public void removeAddress(Address address) {
        this.addresses.remove(address);
    }

}
