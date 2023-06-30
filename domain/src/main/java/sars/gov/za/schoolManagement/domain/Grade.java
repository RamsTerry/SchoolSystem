/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

/**
 *
 * @author S2028398
 */
@Audited
@Entity
@Table(name = "grade")
public class Grade extends BaseEntity {

    @Column(name = "designation")
    private String designation;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private School school;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

}
