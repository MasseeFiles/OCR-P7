package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RuleNameController {

    private static final Logger logger = LogManager.getLogger("RuleNameController");

    @Autowired
    private RuleService ruleService;

    @RequestMapping("/ruleName/list")
    public String home(Model model) {

        logger.info("Requete pour la recherche de toutes les ruleNames");

        List<RuleName> ruleNames = ruleService.findAll();
        model.addAttribute("ruleNames", ruleNames);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {

        logger.info("Requete pour l'affichage du formulaire d'ajout d'un rule");

        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(
            @Valid RuleName ruleName,
            BindingResult result,
            Model model
    ) {

        logger.info("Requete pour la validation et sauvegarde d'une nouvelle rule");

        if (result.hasErrors()) {
            return "ruleName/add";
        } else {
            ruleService.add(ruleName);
        }
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Requete pour l'affichage du formulaire d'update d'une rule");

        RuleName ruleNameToUpdate = ruleService.findById(id);
        model.addAttribute("ruleName", ruleNameToUpdate);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(
            @PathVariable("id") Integer id,
            @Valid RuleName ruleName,
            BindingResult result,
            Model mode
    ) {

        logger.info("Requete pour l'update d'un ruleName");

        if (result.hasErrors()) {
            return "ruleName/update";
        } else {
            ruleService.add(ruleName);
        }
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleService.delete(id);
        return "redirect:/ruleName/list";
    }
}
