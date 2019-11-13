package com.example.usersapi.service;

import com.example.usersapi.config.JwtUtil;
import com.example.usersapi.model.Profile;
import com.example.usersapi.model.User;
import com.example.usersapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<User> listUsers() {
        return null;
    }

    @Override
    public List<String> signUp(User user) {
//        user.setPassword(encoder.encode(user.getPassword()));
        user.setPassword(encoder().encode(user.getPassword()));

        if(userRepository.save(user) != null) {
            return Arrays.asList( jwtUtil.generateToken(user.getUsername()), user.getUsername());

        }
        return null;
    }

    @Override
    public List<String> logIn(User user) {
        User savedUser = userRepository.findByUsername(user.getUsername());
        if( savedUser != null && encoder().matches(user.getPassword(), savedUser.getPassword())) {
            return Arrays.asList( jwtUtil.generateToken(user.getUsername()), savedUser.getUsername());
        }

        return null;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userRepository.findByUsername(username);
//
//        return new org.springframework.security.core.userdetails
//                .User(user.getUsername(),
//                encoder().encode(user.getPassword()), getGrantedAuthorities(user));
//    }
//
//    private List<GrantedAuthority> getGrantedAuthorities(User user) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//
//
////convert the roles into granted authority list
//        return authorities;
//    }

//    @Override
//    public Profile createProfile(Profile profile, String tokenHeader) {
//        String username = jwtUtil.getUsernameFromToken(jwtUtil.pureToken(tokenHeader));
//        User user = userRepository.findByUsername(username);
//        user.setProfile(profile);
//        userRepository.save(user);
//        return user.getProfile();
//    }
//
//    @Override
//    public Profile getProfile(String token) {
//        String username = jwtUtil.getUsernameFromToken(jwtUtil.pureToken(token));
//        User user = userRepository.findByUsername(username);
//        return user.getProfile();
//    }

    @Override
    public Profile updateProfile(Profile profile, String tokenHeader) {
        return null;
    }


//
//    @Override
//    public User searchById(long id) {
//        return userRepository.findById(id).get();
//    }
//
//    @Override
//    public Iterable<User> searchByName(String name) {
//        String normalized = name.trim().toLowerCase();
//        return userRepository.findByFirstNameContainsOrLastNameContains(normalized, normalized);
//    }
//
//    @Override
//    public HttpStatus deleteUser(long id) {
//        userRepository.deleteById(id);
//        return HttpStatus.OK;
//    }
//
//    @Override
//    public HttpStatus createUser(User user) {
//        userRepository.save(user);
//        return HttpStatus.OK;
//    }
//
//    @Override
//    public HttpStatus updateUser(long id, User userRequest) {
//        User user = userRepository.findById(id).get();
//        user.setUserName(userRequest.getUserName());
//        user.setFirstName(userRequest.getFirstName());
//        user.setLastName(userRequest.getLastName());
//        userRepository.save(user);
//        return HttpStatus.OK;
//    }
}
