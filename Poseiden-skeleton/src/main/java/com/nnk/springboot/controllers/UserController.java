package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.UserApp;
import com.nnk.springboot.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private static final Logger logger = LogManager.getLogger("UserController");
    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String home(Model model) {

        logger.info("Requete pour la recherche de tous les userApps");

        List<UserApp> userApps = userService.findAll();
        model.addAttribute("userApps", userService.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUserForm(UserApp userApp) {

        logger.info("Requete pour l'affichage du formulaire d'ajout d'un userApp");

        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(
            @Valid UserApp userApp,
            BindingResult result,
            Model model
    ) {

        logger.info("Requete pour la validation et sauvegarde d'un nouveau userApp");

        if (result.hasErrors()) {
            return "user/add";
        } else {
            //Encodage du password avant persistence en BDD
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userApp.setPassword(encoder.encode(userApp.getPassword()));
            userService.add(userApp);
        }
        return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Requete pour l'affichage du formulaire d'update d'un user");

        UserApp userAppToSearch = userService.findById(id);
        model.addAttribute("userApp", userAppToSearch);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(
            @PathVariable("id") Integer id,
            @Valid UserApp userApp,
            BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {
            return "user/update";
        } else {
            userService.update(userApp);
        }
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.delete(id);
        return "redirect:/user/list";
    }
}
