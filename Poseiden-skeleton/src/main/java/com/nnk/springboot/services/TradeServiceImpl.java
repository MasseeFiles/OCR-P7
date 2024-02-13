package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public List<Trade> findAll() { return tradeRepository.findAll(); }

    @Override
    public Trade findById(Integer id) {
        Trade tradeToFind = tradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trade not found : Id used " + id));

        return tradeToFind;
    }

    @Override
    public void add(Trade trade) { tradeRepository.save(trade); }

    @Override
    public void update(Trade trade) {
        Trade tradeToUpdate = tradeRepository.findById(trade.getTradeId())
                .orElseThrow(() -> new RuntimeException("Trade to update not found : Id used " + trade.getTradeId()));

        tradeRepository.delete(tradeToUpdate);
        tradeRepository.save(trade);
    }

    @Override
    public void delete(Integer id) {
        Trade tradeToDelete = tradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trade to delete not found : Id used " + id));

        tradeRepository.delete(tradeToDelete);
    }
}
