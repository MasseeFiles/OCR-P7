package com.nnk.springboot.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private static final Logger logger = LogManager.getLogger("HomeController");

//    @RequestMapping("/home")
//	public String home(Model model)	{
//
//        logger.info("Requete pour l'affichage de la page Home");

//        return "home";
//}

    @RequestMapping("/home")
    public ModelAndView home() {

        logger.info("Requete pour l'affichage de la page Home");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/home");
        return mav;
    }
}
