//package com.nnk.springboot;
//
//import com.nnk.springboot.domain.BidList;
//import com.nnk.springboot.repositories.BidRepository;
//import org.assertj.core.api.Assert;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class BidTests {
//
//	@Autowired
//	private BidRepository bidListRepository;
//
//	@Test
//	public void bidListTest() {
//		//constructeur à 3 parametres = verifier que le double correspond à bidquantity
//		BidList bidlist = new BidList("Account Test", "Type Test", 10d);
//
//		// Save
//		bidlist = bidListRepository.save(bidlist);
//		Assert.assertNotNull(bidlist.getBidListId());
//		Assert.assertEquals(bidlist.getBidQuantity(), 10d, 10d);
//
//		// Update
//		bidlist.setBidQuantity(20d);
//		bidlist = bidListRepository.save(bidlist);
//		Assert.assertEquals(bidlist.getBidQuantity(), 20d, 20d);
//
//		// Find
//		List<BidList> listResult = bidListRepository.findAll();
//		Assert.assertTrue(listResult.size() > 0);
//
//		// Delete
//		Integer id = bidlist.getBidListId();
//		bidListRepository.delete(bidlist);
//		Optional<BidList> bidList = bidListRepository.findById(id);
//		Assert.assertFalse(bidList.isPresent());
//	}
//}
