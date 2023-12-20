package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Required;

//import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bid_list")
public class BidList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_list_id")
    private Integer BidListId;

    @Column(name = "account")
    String account;

    @Column(name = "type")
    String type;

    @Column(name = "bid_quantity")
    Double bidQuantity;

    @Column(name = "ask_quantity")
    Double askQuantity;

    @Column(name = "bid")
    Double bid;

    @Column(name = "ask")
    Double ask;

    @Column(name = "benchmark")
    String benchmark;

    @Column(name = "bid_list_date")
    //Timestamp : format particulier de date pour utilisation SQL
    Timestamp bidListDate;

    @Column(name = "commentary")
    String commentary;

    @Column(name = "security")
    String security;

    @Column(name = "status")
    String status;

    @Column(name = "trader")
    String trader;

    @Column(name = "book")
    String book;

    @Column(name = "creation_name")
    String creationName;

    @Column(name = "creation_date")
    Timestamp creationDate;

    @Column(name = "revision_name")
    String revisionName;

    @Column(name = "revision_date")
    Timestamp revisionDate;

    @Column(name = "deal_name")
    String dealName;

    @Column(name = "deal_type")
    String dealType;

    @Column(name = "source_list_id")
    String sourceListId;

    @Column(name = "side")
    String side;

    public BidList(String account, String type, double bidQuantity ) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }
}
