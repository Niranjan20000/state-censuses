package com.Etech.census.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Etech.census.Respository.UserRepository;
import com.Etech.census.entity.AwwWorker;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private AwwWorker loggedInWorker; // track the logged-in user

    // Registration (store password as plain text)
    public AwwWorker registerUser(AwwWorker user) {
        return userRepository.save(user);
    }

    // Login using username and password
    public boolean loginUser(String username, String password) {
        Optional<AwwWorker> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            AwwWorker user = userOpt.get();
            if (password.equals(user.getPassword())) {
                loggedInWorker = user;   // ✅ store worker here
                return true;
            }
        }
        return false;
    }

    // Get current worker
    public AwwWorker getLoggedInWorker() {
        return loggedInWorker;
    }

    // Update phone number
    public void updatePhone(String phone) {
        if (loggedInWorker != null) {
            loggedInWorker.setMobile(phone);
            userRepository.save(loggedInWorker); // persist change
        }
    }

    // Logout
    public void logout() {
        loggedInWorker = null;
    }
}