package com.guavus.crudspringboot.service;

import com.guavus.crudspringboot.controllers.UserController;
import com.guavus.crudspringboot.model.User;
import com.guavus.crudspringboot.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User findById(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
    }

    @Override
    public User save(User object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findAllByNameLike(String name) {
        return userRepository.findAllByNameLike(name);
    }

    @Override
    public Boolean emailExists(String email) {
        logger.debug("Email : "+email);
        User existingUser = userRepository.findByEmail(email);
        logger.debug("Existing User : "+existingUser);
        return existingUser != null;
    }
}
