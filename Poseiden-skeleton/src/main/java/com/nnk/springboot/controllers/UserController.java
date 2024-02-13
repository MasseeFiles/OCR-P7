package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@RequiredArgsConstructor
@Controller
public class UserController {
    private static final Logger logger = LogManager.getLogger("UserController");
//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String home(Model model) {

        logger.info("Requete pour la recherche de tous les users");

        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUserForm(User user) {

        logger.info("Requete pour l'affichage du formulaire d'ajout d'un user");

        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(
            @Valid User user,
            BindingResult result,
            Model model) {

        logger.info("Requete pour la validation et sauvegarde d'un nouveau user");

        if (!result.hasErrors()) {

            //TODO : A utiliser apres mise en place de Spring Security
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            user.setPassword(encoder.encode(user.getPassword()));

            userService.add(user);
            model.addAttribute("users", userService.findAll());
            return "redirect:/user/list";

        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Requete pour l'affichage du formulaire d'update d'un user");

        User userToSearch = userService.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        userToSearch.setPassword("");
        model.addAttribute("user", userToSearch);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(
            @PathVariable("id") Integer id,
            @Valid User user,
            BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {
            return "user/update";
        }
//TODO : A utiliser apres mise en place de Spring Security - a mettre dans le service???
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        user.setPassword(encoder.encode(user.getPassword()));

//        user.setPassword(user.getPassword());
//
//        user.setUserId(id);
//        userRepository.save(user);

        userService.update(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.delete(id);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }
}
