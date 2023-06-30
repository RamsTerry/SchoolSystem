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
public enum AssesmentStatus {

    PASSED("Passed"),
    FAILED("Failed"),
    CONDONED("Condoned");

    private final String displayName;

    AssesmentStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
