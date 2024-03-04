package com.nnk.springboot.services;

import com.nnk.springboot.domain.*;
import com.nnk.springboot.repositories.*;
import org.springframework.stereotype.Service;

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
//        Rating[] ratings = new Rating[3];
//        ratings[0] = new Rating(1, "1", "1", "1", 1);
//        ratings[1] = new Rating(2, "2", "2", "2", 2);
//        ratings[2] = new Rating(3, "3", "3", "3", 3);
//        ratingRepository.saveAll(Arrays.asList(ratings));

//        BidList[] bidLists = new BidList[3];
//        bidLists[0] = new BidList(1, "account1", "type1", 1.0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
//        bidLists[1] = new BidList(2, "account2", "type2", 1.0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
//        bidLists[2] = new BidList(3, "account3", "type3", 1.0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
//        bidListRepository.saveAll(Arrays.asList(bidLists));

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
        userApps[0] = new UserApp(1, "userName1", "$2a$12$2NljUmASdUls6s8BtEUTtOkQOjtkiFBCX/IOOtoxcYECq4kplgJVO", "user1FullName", "admin"); //Pass = a
        userApps[1] = new UserApp(2, "userName2", "$2a$12$2NljUmASdUls6s8BtEUTtOkQOjtkiFBCX/IOOtoxcYECq4kplgJVO", "user2FullName", "user");
        userApps[2] = new UserApp(3, "userName3", "$2a$12$2NljUmASdUls6s8BtEUTtOkQOjtkiFBCX/IOOtoxcYECq4kplgJVO", "user3FullName", "admin");
        userRepository.saveAll(Arrays.asList(userApps));
    }
}
