package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;

@Service
//@Profile("test")
public class DBDataInitializerTest {
    public DBDataInitializerTest(RatingRepository ratingRepository, BidListRepository bidListRepository){   //ajouter bcryptencoder apres configuration de springsecurity

        Rating[] ratings = new Rating[3];
        ratings[0] = new Rating(1, "11", "22" , "33" , 1);
        ratings[1] = new Rating(2, "44", "55" , "66" , 2);
        ratings[2] = new Rating(3, "77", "88" , "99" , 3);
        ratingRepository.saveAll(Arrays.asList(ratings));

        BidList[] bidLists = new BidList[3];
        long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        bidLists[0] = new BidList(1, "account1", "type1", 1.0 , 1.0 , 1.0, 1.0, "benchmark1", timestamp, "commentary1", "security1", "status1", "trader1", "book1", "creationName1", timestamp, "revisionName1", timestamp, "dealName1", "dealType1", "sourceListId1", "side1" );
        bidLists[1] = new BidList(2, "account2", "type2", 1.0 , 1.0 , 1.0, 1.0, "benchmark1", timestamp, "commentary1", "security1", "status1", "trader1", "book1", "creationName1", timestamp, "revisionName1", timestamp, "dealName1", "dealType1", "sourceListId1", "side1" );
        bidLists[2] = new BidList(3, "account3", "type3", 1.0 , 1.0 , 1.0, 1.0, "benchmark1", timestamp, "commentary1", "security1", "status1", "trader1", "book1", "creationName1", timestamp, "revisionName1", timestamp, "dealName1", "dealType1", "sourceListId1", "side1" );

        bidListRepository.saveAll(Arrays.asList(bidLists));
    }
}
