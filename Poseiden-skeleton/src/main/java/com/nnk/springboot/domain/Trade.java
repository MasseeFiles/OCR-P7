package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer tradeId;

    @NotBlank(message = "Account cannot be blank")
    private String account;

    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotBlank(message = "Buy quantity cannot be blank")
    private Double buyQuantity;

    @NotBlank(message = "Sell quantity cannot be blank")
    private Double sellQuantity;

    @NotBlank(message = "Buy price cannot be blank")
    private Double buyPrice;

    @NotBlank(message = "Sell price cannot be blank")
    private Double sellPrice;

    @NotBlank(message = "Benchmark cannot be blank")
    private String benchmark;

    @NotBlank(message = "Trade date cannot be blank")
    private Timestamp tradeDate;

    @NotBlank(message = "Security cannot be blank")
    private String security;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @NotBlank(message = "Trader cannot be blank")
    private String trader;

    @NotBlank(message = "Book cannot be blank")
    private String book;

    @NotBlank(message = "Creation name cannot be blank")
    private String creationName;

    @NotBlank(message = "Creation date cannot be blank")
    private Timestamp creationDate;

    @NotBlank(message = "Revision name cannot be blank")
    private String revisionName;

    @NotBlank(message = "Revision date cannot be blank")
    private Timestamp revisionDate;

    @NotBlank(message = "Deal name cannot be blank")
    private String dealName;

    @NotBlank(message = "Deal type cannot be blank")
    private String dealType;

    @NotBlank(message = "Sourcelist id cannot be blank")
    private String sourceListId;

    @NotBlank(message = "Side cannot be blank")
    private String side;
}
