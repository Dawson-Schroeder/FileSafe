package com.example.filesafe.services;

import com.example.filesafe.models.FileSafeUserDetails;
import com.example.filesafe.models.User;
import com.example.filesafe.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FileSafeUserDetailsService implements UserDetailsService {
    public final UserRepository usersDao;

    public FileSafeUserDetailsService(UserRepository usersDao){
        this.usersDao = usersDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User details not found for user: " + username);
        } else {
            return new FileSafeUserDetails(user.getId(), user.getEmail(), user.getUsername(), user.getPassword());
        }
    }

}
