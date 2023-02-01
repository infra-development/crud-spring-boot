package com.guavus.crudspringboot.service;

import com.guavus.crudspringboot.model.User;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<User> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
