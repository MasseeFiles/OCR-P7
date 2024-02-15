package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {
    private final TradeRepository tradeRepository;

    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade findById(Integer id) {

        return tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trade not found : Id used " + id));
    }

    @Override
    public void add(Trade trade) {
        tradeRepository.save(trade);
    }

    @Override
    public void update(Trade trade) {
        Trade tradeToUpdate = tradeRepository.findById(trade.getTradeId())
                .orElseThrow(() -> new IllegalArgumentException("Trade to update not found : Id used " + trade.getTradeId()));

        tradeRepository.delete(tradeToUpdate);
        tradeRepository.save(trade);
    }

    @Override
    public void delete(Integer id) {
        Trade tradeToDelete = tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trade to delete not found : Id used " + id));

        tradeRepository.delete(tradeToDelete);
    }
}
