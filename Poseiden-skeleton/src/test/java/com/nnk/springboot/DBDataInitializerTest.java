package com.nnk.springboot;

import com.nnk.springboot.domain.*;
import com.nnk.springboot.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
//@Profile("test")
public class DBDataInitializerTest {
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    BidListRepository bidListRepository;
    @Autowired
    CurvePointRepository curvePointRepository;
    @Autowired
    RuleNameRepository ruleNameRepository;
    @Autowired
    TradeRepository tradeRepository;
    @Autowired
    UserRepository userRepository;

    @Bean
    public DBDataInitializerTest bBDataInitializerTest() {

        Rating[] ratings = new Rating[3];
        ratings[0] = new Rating(1, "AA", "BB", "CC", 1);
        ratings[1] = new Rating(2, "DD", "EE", "FF", 2);
        ratings[2] = new Rating(3, "GG", "HH", "II", 3);
        ratingRepository.saveAll(Arrays.asList(ratings));

        BidList[] bidLists = new BidList[3];
        bidLists[0] = new BidList(1, "account1", "type1", 1.0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        bidLists[1] = new BidList(2, "account2", "type2", 1.0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        bidLists[2] = new BidList(3, "account3", "type3", 1.0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        bidListRepository.saveAll(Arrays.asList(bidLists));

        CurvePoint[] curvePoints = new CurvePoint[3];
        curvePoints[0] = new CurvePoint(1, null, null, 1.0, 1.0, null);
        curvePoints[1] = new CurvePoint(2, null, null, 2.0, 2.0, null);
        curvePoints[2] = new CurvePoint(3, null, null, 3.0, 3.0, null);
        curvePointRepository.saveAll(Arrays.asList(curvePoints));

        RuleName[] ruleNames = new RuleName[3];
        ruleNames[0] = new RuleName(1, "name1", "description1", "json1", "template1", "sqlStr1", "sqlPart1");
        ruleNames[1] = new RuleName(2, "name2", "description2", "json2", "template2", "sqlStr2", "sqlPart2");
        ruleNames[2] = new RuleName(3, "name3", "description3", "json3", "template3", "sqlStr3", "sqlPart3");
        ruleNameRepository.saveAll(Arrays.asList(ruleNames));

        Trade[] trades = new Trade[3];
        trades[0] = new Trade(1, "account1", "type1", 1.0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        trades[1] = new Trade(2, "account2", "type2", 2.0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        trades[2] = new Trade(3, "account3", "type3", 3.0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        tradeRepository.saveAll(Arrays.asList(trades));

        UserApp[] userApps = new UserApp[3];
        userApps[0] = new UserApp(1, "user1", "$2a$13$3NCtQK6iXRU/9B4R660bx./RSHQ6yBNagqzCS7rXSBF6izzhlpf5a", "user1FullName", "admin");
        userApps[1] = new UserApp(2, "user2", "$2a$13$3NCtQK6iXRU/9B4R660bx./RSHQ6yBNagqzCS7rXSBF6izzhlpf5a", "user2FullName", "user");
        userApps[2] = new UserApp(3, "user3", "$2a$13$3NCtQK6iXRU/9B4R660bx./RSHQ6yBNagqzCS7rXSBF6izzhlpf5a", "user3FullName", "admin");
        userRepository.saveAll(Arrays.asList(userApps));

        return new DBDataInitializerTest();
    }
}
