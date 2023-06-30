/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.common;

/**
 *
 * @author S2028398
 */
public enum Gender {

    MALE("Male"),
    FEMALE("Female");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
