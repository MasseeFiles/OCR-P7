package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {
    private static final Logger logger = LogManager.getLogger("TradeController");
    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {

        logger.info("Requete pour la recherche de tous les trades");

        List<Trade> trades = tradeService.findAll();
        model.addAttribute("trades", trades);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTradeForm(Trade trade) {

        logger.info("Requete pour l'affichage du formulaire d'ajout d'un trade");

        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(
            @Valid Trade trade,
            BindingResult result,
            Model model
    ) {

        logger.info("Requete pour la validation et sauvegarde d'un nouveau trade");

        if (result.hasErrors()) {
            throw new IllegalArgumentException("Trade provided is not valid - Id used : " + trade.getTradeId());
        } else {
            tradeService.add(trade);
        }
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Requete pour l'affichage du formulaire d'update d'un trade");

        Trade tradeToUpdate = tradeService.findById(id);
        model.addAttribute("trade", tradeToUpdate);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(
            @PathVariable("id") Integer id,
            @Valid Trade trade,
            BindingResult result,
            Model model
    ) {

        logger.info("Requete pour l'update d'un trade");

        if (result.hasErrors()) {
            throw new IllegalArgumentException("Trade provided is not valid - Id used : " + trade.getTradeId());
        } else {
            tradeService.update(trade);
        }
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.delete(id);
        return "redirect:/trade/list";
    }
}
