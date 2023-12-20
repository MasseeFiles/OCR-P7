package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BidListServiceImpl implements BidListService {
    @Autowired
    private BidRepository bidRepository;

    @Override
    public List<BidList> findAll() {
        return bidRepository.findAll();
    }

    @Override
    public boolean validate(BidList bidlist) {
        //traitement sur @valid bidlist???
        return true;
    }

    @Override
    public void save(BidList bidlist) {
        bidRepository.save(bidlist);
    }

    @Override
    public BidList findBidlistById(Integer id) {
            return null;
//        return bidRepository.findBidListById(id);
    }

    @Override
    public void delete(BidList bidListToDelete) {
        bidRepository.delete(bidListToDelete);
    }


}
