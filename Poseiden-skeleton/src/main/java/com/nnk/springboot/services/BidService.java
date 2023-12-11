package com.nnk.springboot.services;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BidService {
    @Autowired
    private BidRepository bidRepository;

    public List<Bid> findAll() {
        return bidRepository.findAll();
    }
}
