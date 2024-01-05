package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bid_list")
public class BidList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bidListId;

    @NotBlank(message = "Account cannot be blank")
    String account;

    @NotBlank(message = "Type cannot be blank")
    String type;

    @NotNull(message = "Bid quantity cannot be blank")
    Double bidQuantity;

    @NotNull(message = "Ask quantity cannot be blank")
    Double askQuantity;

    @NotNull(message = "Bid cannot be blank")
    Double bid;

    @NotNull(message = "Ask cannot be blank")
    Double ask;

    @NotBlank(message = "Benchmark cannot be blank")
    String benchmark;

    @NotNull(message = "Bid list date cannot be blank")
    Timestamp bidListDate;      //Timestamp : format particulier de date pour utilisation SQL

    @NotBlank(message = "Commentary cannot be blank")
    String commentary;

    @NotBlank(message = "Security cannot be blank")
    String security;

    @NotBlank(message = "Status cannot be blank")
    String status;

    @NotBlank(message = "Trader cannot be blank")
    String trader;

    @NotBlank(message = "Book cannot be blank")
    String book;

    @NotBlank(message = "Creation name cannot be blank")
    String creationName;

    @NotNull(message = "Creation date cannot be blank")
    Timestamp creationDate;

    @NotBlank(message = "Revision name cannot be blank")
    String revisionName;

    @NotNull(message = "Revision date cannot be blank")
    Timestamp revisionDate;

    @NotBlank(message = "Deal name cannot be blank")
    String dealName;

    @NotBlank(message = "Deal type cannot be blank")
    String dealType;

    @NotBlank(message = "Source list Id cannot be blank")
    String sourceListId;

    @NotBlank(message = "Side cannot be blank")
    String side;
}
