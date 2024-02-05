package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

import java.util.List;

public interface BidListService {
    List<BidList> findAll();

    BidList findById(Integer id);

    void add(BidList bidList);

    void update(BidList bidList);

    void delete(Integer id);
}
