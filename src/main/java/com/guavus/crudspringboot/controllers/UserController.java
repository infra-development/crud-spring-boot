package com.guavus.crudspringboot.controllers;

import com.guavus.crudspringboot.model.User;
import com.guavus.crudspringboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "users/add-user";

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
        return "users/newuser";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users/update";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        userService.deleteById(id);
        return "redirect:/";

    }

}