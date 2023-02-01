package com.guavus.crudspringboot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "name")
    @NotBlank(message = "Name can not be empty")
    private String name;

    @Column(name = "email")
    @Email(message = "Enter valid email address")
    private String email;

    @Builder
    public User(Long id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

}

