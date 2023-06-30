/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.service;

import java.util.List;
import sars.gov.za.schoolManagement.domain.User;

/**
 *
 * @author S2028398
 */
public interface SystemUserServiceLocal {
    User save(User systemUser);

    User findById(Long id);

    User update(User systemUser);

    void deleteAll();

    User deleteById(Long id);

    List<User> listAll();

    boolean isExist(User systemUser);
    User findUserByUsernameAndPasswords(String username, String passwords);
    User findUserByIdentifier(String identifier);
}
