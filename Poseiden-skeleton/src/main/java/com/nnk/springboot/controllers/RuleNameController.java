//package com.nnk.springboot.controllers;
//
//import com.nnk.springboot.domain.Rule;
//import com.nnk.springboot.services.RuleService;
//import lombok.RequiredArgsConstructor;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Controller
//public class RuleNameController {
//
//    private static final Logger logger = LogManager.getLogger("RuleNameController");
//
//    @Autowired
//    private RuleService ruleService;
//
//    @RequestMapping("/ruleName/list")
//    public String home(Model model) {
//
//        logger.info("Requete pour la recherche de toutes les rules");
//
//        List<Rule> rules = ruleService.findAll();
//        model.addAttribute("rule", rules);
//        return "ruleName/list";
//    }
//
//    @GetMapping("/ruleName/add")
//    public String addRuleForm(Rule ruleName) {
//
//        logger.info("Requete pour l'affichage du formulaire d'ajout d'un rule");
//
//        return "ruleName/add";
//    }
//
//    @PostMapping("/ruleName/validate")
//    public String validate(
//            @Valid Rule ruleName,
//            BindingResult result,
//            Model model) {
//
//        logger.info("Requete pour la validation et sauvegarde d'une nouvelle rule");
//
//        if (result.hasErrors()) {
//            throw new RuntimeException("Rule provided is not valid - Id used : " + ruleName.getRuleId());
//        } else {
//            ruleService.save(ruleName);
//        }
//        return "ruleName/add";
//    }
//
//    @GetMapping("/ruleName/update/{id}")
//    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
//
//        logger.info("Requete pour l'affichage du formulaire d'update d'une rule");
//
//        Rule ruleToUpdate = ruleService.findById(id);
//        model.addAttribute("rule", ruleToUpdate);
//        return "ruleName/update";
//    }
//
//    @PostMapping("/ruleName/update/{id}")
//    public String updateRuleName(
//            @PathVariable("id") Integer id,
//            @Valid Rule ruleName,
//            BindingResult result,
//            Model mode
//    ) {
//
//        if (result.hasErrors()) {
//            throw new RuntimeException("RuleName provided is not valid - Id used : " + ruleName.getRuleId());
//        } else {
//            ruleService.save(ruleName);
//        }
//        return "redirect:/ruleName/list";
//    }
//
//    @GetMapping("/ruleName/delete/{id}")
//    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
//        ruleService.delete(id);
//        return "redirect:/ruleName/list";
//    }
//}
