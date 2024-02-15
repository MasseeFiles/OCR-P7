package com.nnk.springboot.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@RequiredArgsConstructor
@Controller
public class HomeController {
    private static final Logger logger = LogManager.getLogger("HomeController");

    @RequestMapping("/")
	public String home(Model model)	{

        logger.info("Requete pour l'affichage de la page Home - role User");

        return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {

        logger.info("Requete pour l'affichage de la page Home - role Admin");

        return "redirect:/bidList/list";
	}


}
