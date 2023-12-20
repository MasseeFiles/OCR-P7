package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

import java.util.List;

public interface BidListService {
    List<BidList> findAll();

    boolean validate(BidList bidlist);

    void save(BidList bidlist);

    BidList findBidlistById(Integer id);

    void delete(BidList bidListToDelete);
}
