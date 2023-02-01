package com.guavus.crudspringboot.controllers;

import com.guavus.crudspringboot.model.User;
import com.guavus.crudspringboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;
    private static final String USER_CREATE_FORM = "users/newuser";
    private static final String USER_UPDATE_FORM = "users/update";
    private static final String REDIRECT_TO_ROOT = "redirect:/";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("alluserslist", userService.findAll());
        return "index";
    }

    @GetMapping("/addnew")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return USER_CREATE_FORM;
    }

    @PostMapping("/save")
    public String saveUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return USER_CREATE_FORM;
        }
        if(userService.emailExists(user.getEmail())) {
            model.addAttribute("errorMessage", "We couldn't process with this email");
            return USER_CREATE_FORM;
        }
        userService.save(user);
        return REDIRECT_TO_ROOT;
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return USER_UPDATE_FORM;
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        userService.deleteById(id);
        return REDIRECT_TO_ROOT;

    }

}