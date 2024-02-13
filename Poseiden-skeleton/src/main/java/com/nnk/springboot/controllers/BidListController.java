package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
public class BidListController {
    private static final Logger logger = LogManager.getLogger("BidListController");
    @Autowired
    private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model) {

        logger.info("Requete pour la recherche de tous les bidLists");

        List<BidList> bidLists = bidListService.findAll();
        model.addAttribute("bidLists", bidLists);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bidlist) {

        logger.info("Requete pour l'affichage du formulaire d'ajout d'un bidlist");

        return "bidList/add";
    }

    @PostMapping("/bidList/validate")   //CREATE
    public String validate(
            @Valid BidList bidlist,         //@Valid : annotation SpringBoot permettant de verifier les contraintes sur objet BID (Bean Validation provider - Hibernate Validator)
            BindingResult result,
            Model model
    ) {

        logger.info("Requete pour la validation et sauvegarde d'une nouvelle bidlist");

        if (result.hasErrors()) {
            throw new IllegalArgumentException("Bidlist provided is not valid - Id used : " + bidlist.getId());
        } else {
            bidListService.add(bidlist);
        }
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}") //Affichage du form UPDATE
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Requete pour l'affichage du formulaire d'update d'un bidlist");

        BidList bidListToSearch = bidListService.findById(id);
        model.addAttribute("bidList", bidListToSearch);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")    //UPDATE
    public String updateBid(
            @PathVariable("id") Integer id,
            @Valid BidList bidList,
            BindingResult result,
            Model model
    ) {

        logger.info("Requete pour l'update d'un bidlist");

        if (result.hasErrors()) {
            throw new IllegalArgumentException("BidList provided is not valid - Id used : " + bidList.getId());
        } else {
            bidListService.update(bidList);
        }
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")     //DELETE
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.delete(id);
        return "redirect:/bidList/list";
    }
}
