package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login");

//        String viewName = "You are not authorized for the requested data.";
//        mav.addObject("view", viewName);
//        mav.getModel().put("view", "/login");
//        mav.setViewName("/login");
        return mav;
    }


//    @GetMapping("/secure/article-details")
//    public ModelAndView getAllUserArticles() {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("users", userService.findAll());
//        mav.setViewName("/user/list");
//        return mav;
//    }
    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("/403");
        return mav;
    }


}
