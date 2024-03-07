package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
//@RequestMapping("app")
public class LoginController {

    @Autowired
    private UserService userService;
//TODO : effacer si pas besoin de controller sur uri /login
//    @GetMapping("/login")
//    public ModelAndView login() {
//        ModelAndView mav = new ModelAndView();
//
//    //Option1
////        String viewName = "/login";
////        mav.addObject("view", viewName);
//
//    //Option2
////        mav.getModel().put("view", "/login");
//
//    //Option3
////        mav.setViewName("/login");
//
//        return mav;
//    }

    @GetMapping("/secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userService.findAll());
        mav.setViewName("/user/list");
        return mav;
    }
}
