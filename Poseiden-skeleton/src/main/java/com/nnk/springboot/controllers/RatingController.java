package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class RatingController {
    private static final Logger logger = LogManager.getLogger("RatingController");

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model) {

        logger.info("Requete pour la recherche de tous les ratings");

        List<Rating> ratings = ratingService.findAll();
        model.addAttribute("ratings", ratings);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String remoteUserName = authentication.getName();
        model.addAttribute("remoteUser", remoteUserName);

        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {

        logger.info("Requete pour l'affichage du formulaire d'ajout d'un rating");

        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(
            @Valid Rating rating,
            BindingResult result,
            Model model
    ) {

        logger.info("Requete pour la validation et sauvegarde d'un nouveau rating");

        if (result.hasErrors()) {
            return "rating/add";
        } else {
            ratingService.add(rating);
        }
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Requete pour l'affichage du formulaire d'update d'un rating");

        Rating ratingToSearch = ratingService.findById(id);
        model.addAttribute("rating", ratingToSearch);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(
            @PathVariable("id") Integer id,
            @Valid Rating rating,
            BindingResult result,
            Model model
    ) {

        logger.info("Requete pour l'update d'un rating");

        if (result.hasErrors()) {
            return "rating/update";
        } else {
            ratingService.update(rating);
        }
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.delete(id);
        return "redirect:/rating/list";
    }
}
