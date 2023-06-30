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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

/**
 *
 * @author S2028398
 */
@Audited
@Entity
@Table(name = "subject")
public class Subject extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private School school;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

}
