package com.krillinator.springSecurityLektion.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserModelService implements UserDetailsService {

    private final UserModelRepository userModelRepository;

    @Autowired
    public UserModelService(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> optionalUserModel = userModelRepository.findByUsername(username);
        UserModel userModel = optionalUserModel.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userModel;
    }

    public UserModel getUserFromAuthentication(Authentication authentication) {
        String username = authentication.getName();
        Optional<UserModel> optionalUser = userModelRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }    

    public void saveUser(UserModel user) {
        userModelRepository.save(user);
    }

    public void deleteUser(UserModel user) {
        userModelRepository.delete(user);
    }

    public void editUser(UserModel user) {
        userModelRepository.save(user);
    }
}
