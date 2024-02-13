package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TradeService {
    List<Trade> findAll();

    Trade findById(Integer id);

    void add(Trade trade);

    void update(Trade trade);

    void delete(Integer id);
}
