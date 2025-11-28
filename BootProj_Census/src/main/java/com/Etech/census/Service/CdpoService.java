package com.Etech.census.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Etech.census.Respository.CdpoRepository;
import com.Etech.census.entity.Cdpo;

@Service
public class CdpoService {

    @Autowired
    private CdpoRepository cdpoRepository;

    private Cdpo loggedInCdpo; // track logged-in officer

    // Registration
    public Cdpo registerCdpo(Cdpo cdpo) {
        return cdpoRepository.save(cdpo);
    }

    // Validation helpers
    public boolean existsByUsername(String username) {
        return cdpoRepository.existsByUsername(username);
    }

    public boolean existsByMobile(String mobile) {
        return cdpoRepository.existsByMobile(mobile);
    }

    // Login
    public boolean loginCdpo(String username, String password) {
        Optional<Cdpo> cdpoOpt = cdpoRepository.findByUsername(username);
        if (cdpoOpt.isPresent()) {
            Cdpo officer = cdpoOpt.get();
            if (password.equals(officer.getPassword())) {
                loggedInCdpo = officer; // store logged-in officer
                return true;
            }
        }
        return false;
    }

    public Cdpo getLoggedInCdpo() {
        return loggedInCdpo;
    }

    public void logout() {
        loggedInCdpo = null;
    }
}