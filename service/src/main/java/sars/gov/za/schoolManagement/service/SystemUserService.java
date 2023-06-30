/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sars.gov.za.schoolManagement.domain.User;
import sars.gov.za.schoolManagement.persistence.SystemUserRepository;

/**
 *
 * @author S2028398
 */
@Service
public class SystemUserService implements SystemUserServiceLocal {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Override
    public User save(User systemUser) {
        return systemUserRepository.save(systemUser);
    }

    @Override
    public User findById(Long id) {
        return systemUserRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("The requested id [ " + id + " ] does nor exist."));
    }

    @Override
    public User update(User systemUser) {
        return systemUserRepository.save(systemUser);
    }

    @Override
    public void deleteAll() {
        systemUserRepository.deleteAll();
    }

    @Override
    public User deleteById(Long id) {
        User systemUser = findById(id);
        if (systemUser != null) {
            systemUserRepository.deleteById(id);
        }
        return systemUser;
    }

    @Override
    public List<User> listAll() {
        return systemUserRepository.findAll();
    }

    @Override
    public boolean isExist(User systemUser) {
        return systemUserRepository.findById(systemUser.getId()) != null;
    }

    @Override
    public User findUserByUsernameAndPasswords(String username, String passwords) {
        return systemUserRepository.findUserByUsernameAndPasswords(username, passwords);
    }

    @Override
    public User findUserByIdentifier(String identifier) {
    return systemUserRepository.findUserByIdentifier(identifier);
    }

}
