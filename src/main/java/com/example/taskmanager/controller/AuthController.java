package com.example.taskmanager.controller;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.UserRepository;
//import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    //    private UserService userService;
    private Long flag;

    @GetMapping("/singupform")
    public ModelAndView signupform() {
        ModelAndView mav = new ModelAndView("register-view");
        User user = new User();
        mav.addObject("user", user);
        return mav;

    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        flag = (user.getRole().getId());

        userRepository.save(user);
        return "redirect:/list";
    }

    @GetMapping("/signinform")
    public ModelAndView signinform() {
        ModelAndView mav = new ModelAndView("signin-view");
        User user = new User();
        mav.addObject("user", user);
        return mav;

    }

    @GetMapping("/signin")
    public String signin(@ModelAttribute User user) {
        user = userRepository.findByUsername(user.getUsername());
        if (user != null) {
            flag = (user.getRole().getId());
            return "redirect:/list";
        } else {
            return "BAD";
        }
    }

    @GetMapping("/list")
    public ModelAndView list() {
        if (flag == 0) {
            return new ModelAndView("student-view");

        } else if (flag == 1) {
            return new ModelAndView("teacher-view");

        } else return new ModelAndView("bad");

    }
}
