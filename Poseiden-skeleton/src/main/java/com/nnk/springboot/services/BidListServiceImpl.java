package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListServiceImpl implements BidListService {
    private final BidListRepository bidListRepository;
    public BidListServiceImpl(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    @Override
    public List<BidList> findAll() { return bidListRepository.findAll();
    }

    @Override
    public BidList findById(Integer id) {
        return bidListRepository.findById(id)     //optional<rating>
                .orElseThrow(() -> new IllegalArgumentException("BidList not found : Id used " + id));
    }

    @Override
    public void add(BidList bidListToAdd) {
        bidListRepository.save(bidListToAdd);
    }

    @Override
    public void update(BidList bidList) {
        BidList bidListToUpdate = bidListRepository.findById(bidList.getId())
                .orElseThrow(() -> new IllegalArgumentException("BidList to update not found : Id used " + bidList.getId()));

        bidListToUpdate.setAccount(bidList.getAccount());
        bidListToUpdate.setType(bidList.getType());
        bidListToUpdate.setBidQuantity(bidList.getBidQuantity());

        bidListRepository.save(bidListToUpdate);
    }

    @Override
    public void delete(Integer id) {
        BidList bidListToDelete = bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("BidList to delete not found : Id used " + id));

        bidListRepository.delete(bidListToDelete);
    }
}
