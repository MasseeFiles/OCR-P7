package com.nnk.springboot.services;

import com.nnk.springboot.domain.*;
import com.nnk.springboot.repositories.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;

@Service
public class DBDataInitializerApp{
    public DBDataInitializerApp(RatingRepository ratingRepository,
                                BidListRepository bidListRepository,
                                CurvePointRepository curvePointRepository,
                                RuleNameRepository ruleNameRepository,
                                TradeRepository tradeRepository,
                                UserRepository userRepository
    ) {

        long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        Rating[] ratings = new Rating[3];
        ratings[0] = new Rating(1, "11", "22", "33", 1);
        ratings[1] = new Rating(2, "44", "55", "66", 2);
        ratings[2] = new Rating(3, "77", "88", "99", 3);
        ratingRepository.saveAll(Arrays.asList(ratings));

        BidList[] bidLists = new BidList[3];
        bidLists[0] = new BidList(1, "account1", "type1", 1.0, 1.0, 1.0, 1.0, "benchmark1", timestamp, "commentary1", "security1", "status1", "trader1", "book1", "creationName1", timestamp, "revisionName1", timestamp, "dealName1", "dealType1", "sourceListId1", "side1");
        bidLists[1] = new BidList(2, "account2", "type2", 1.0, 1.0, 1.0, 1.0, "benchmark1", timestamp, "commentary1", "security1", "status1", "trader1", "book1", "creationName1", timestamp, "revisionName1", timestamp, "dealName1", "dealType1", "sourceListId1", "side1");
        bidLists[2] = new BidList(3, "account3", "type3", 1.0, 1.0, 1.0, 1.0, "benchmark1", timestamp, "commentary1", "security1", "status1", "trader1", "book1", "creationName1", timestamp, "revisionName1", timestamp, "dealName1", "dealType1", "sourceListId1", "side1");
        bidListRepository.saveAll(Arrays.asList(bidLists));

        CurvePoint[] curvePoints = new CurvePoint[3];
        curvePoints[0] = new CurvePoint(1, 1, timestamp, 1.0, 1.0, timestamp);
        curvePoints[1] = new CurvePoint(2, 2, timestamp, 2.0, 2.0, timestamp);
        curvePoints[2] = new CurvePoint(3, 3, timestamp, 3.0, 3.0, timestamp);
        curvePointRepository.saveAll(Arrays.asList(curvePoints));

        RuleName[] ruleNames = new RuleName[3];
        ruleNames[0] = new RuleName(1, "name1", "description1", "json1", "template1", "sqlStr1", "sqlPart1");
        ruleNames[1] = new RuleName(2, "name2", "description2", "json2", "template2", "sqlStr2", "sqlPart2");
        ruleNames[2] = new RuleName(3, "name3", "description3", "json3", "template3", "sqlStr3", "sqlPart3");
        ruleNameRepository.saveAll(Arrays.asList(ruleNames));

        Trade[] trades = new Trade[3];
        trades[0] = new Trade(1, "account1", "type1", 1.0, 1.0, 1.0, 1.0, "benchmark1", timestamp, "security1", "status1", "trader1", "book1", "creationName1", timestamp, "revisionName1", timestamp, "dealName1", "dealType1", "sourceListId1", "side1");
        trades[1] = new Trade(2, "account2", "type2", 2.0, 2.0, 2.0, 2.0, "benchmark2", timestamp, "security2", "status2", "trader2", "book2", "creationName2", timestamp, "revisionName2", timestamp, "dealName2", "dealType2", "sourceListId2", "side2");
        trades[2] = new Trade(3, "account3", "type3", 3.0, 3.0, 3.0, 3.0, "benchmark3", timestamp, "security3", "status3", "trader3", "book3", "creationName3", timestamp, "revisionName3", timestamp, "dealName3", "dealType3", "sourceListId3", "side3");
        tradeRepository.saveAll(Arrays.asList(trades));

        User[] users = new User[3];
        users[0] = new User(1, "tom", "pass1", "tomName","admin");
        users[1] = new User(2, "jack", "pass2", "jackName", "user");
        users[2] = new User(3, "lily", "pass3", "lilyName", "admin");
        userRepository.saveAll(Arrays.asList(users));
    }
}
