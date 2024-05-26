package com.example.userwithroles.service;

import com.example.userwithroles.entities.User;
import com.example.userwithroles.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return user;
        } else {

            return null;
        }
    }

    public String createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRoles() == null) {
            user.setRoles("USER");

            User result = userRepository.save(user);

            if (result.getId() > 0) {

                return "User was saved";
            } else {

                return "ERROR: User not saved";
            }
        }
        else if (user.getRoles() != null) {

            if (user.getRoles().equals("ADMIN") || user.getRoles().equals("USER")) {

                User result = userRepository.save(user);

                if (result.getId() > 0) {

                    return "User was saved";
                } else {

                    return "ERROR: User not saved";
                }


            } else {

                return "ERROR: Roles has to be set as either ADMIN or USER";
            }
        } else {
            return "ERROR";
        }

    }

    public Object findMyDetails() {
        return userRepository.findByEmail(findLoggedInUserDetails().getUsername());
    }

    public UserDetails findLoggedInUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        } else {

            return null;
        }
    }
}
