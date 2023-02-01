package com.guavus.crudspringboot.service;

import com.guavus.crudspringboot.model.User;

import java.util.List;

public interface UserService extends CrudService<User, Long> {

    User findByName(String name);

    List<User> findAllByNameLike(String name);

    Boolean emailExists(String email);
}
