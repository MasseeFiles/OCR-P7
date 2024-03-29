package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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
public class CurvePointController {
    private static final Logger logger = LogManager.getLogger("CurvePointController");
    @Autowired
    private CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {

        logger.info("Requete pour la recherche de tous les curvePoints");

        List<CurvePoint> curvePoints = curvePointService.findAll();
        model.addAttribute("curvePoints", curvePoints);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String remoteUserName = authentication.getName();
        model.addAttribute("remoteUser", remoteUserName);

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvePoint) {

        logger.info("Requete pour l'affichage du formulaire d'ajout d'un curvePoint");

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(
            @Valid CurvePoint curvePoint,
            BindingResult result,
            Model model
    ) {

        logger.info("Requete pour la validation et sauvegarde d'un nouveau curvePoint");

        if (result.hasErrors()) {
            return "/curvePoint/add";
        } else {
            curvePointService.add(curvePoint);
        }
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Requete pour l'affichage du formulaire d'update d'un curvePoint");

        CurvePoint curvePointToSearch = curvePointService.findById(id);
        model.addAttribute("curvePoint", curvePointToSearch);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(
            @PathVariable("id") Integer id,
            @Valid CurvePoint curvePoint,
            BindingResult result,
            Model model
    ) {

        logger.info("Requete pour l'update d'un curvePoint");

        if (result.hasErrors()) {
            return "curvePoint/update";
        } else {
            curvePointService.update(curvePoint);
        }
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curvePointService.delete(id);
        return "redirect:/curvePoint/list";
    }
}
