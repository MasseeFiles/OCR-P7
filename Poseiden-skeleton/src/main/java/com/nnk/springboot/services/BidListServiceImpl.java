//package com.nnk.springboot.services;
//
//import com.nnk.springboot.domain.BidList;
//import com.nnk.springboot.repositories.BidRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BidListServiceImpl implements BidListService {
//    @Autowired
//    private BidRepository bidRepository;
//
//    @Override
//    public List<BidList> findAll() {
//        return bidRepository.findAll();
//    }
//
//    @Override
//    public BidList findById(Integer id) {
//        BidList bidListToFind = bidRepository.findById(id)     //optional<rating>
//                .orElseThrow(() -> new RuntimeException("BidList not found : Id used " + id));
//
//        return bidListToFind;
//    }
//
//    @Override
//    public void save(BidList bidlist) {
//        bidRepository.save(bidlist);
//    }
//
//    @Override
//    public void update(BidList bidList) {
//        BidList bidListToUpdate = bidRepository.findById(bidList.getBidListId())
//                .orElseThrow(() -> new RuntimeException("BidList to update not found : Id used " + bidList.getBidListId()));
//
//        bidRepository.delete(bidListToUpdate);
//        bidRepository.save(bidList);
//    }
//
//    @Override
//    public void delete(Integer id) {
//        BidList bidListToDelete = bidRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("BidList to delete not found : Id used " + id));
//
//        bidRepository.delete(bidListToDelete);
//    }
//}
