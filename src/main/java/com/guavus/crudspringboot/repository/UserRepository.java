package com.guavus.crudspringboot.repository;

import com.guavus.crudspringboot.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);

    User findByEmail(String email);

    List<User> findAllByNameLike(String name);
}
