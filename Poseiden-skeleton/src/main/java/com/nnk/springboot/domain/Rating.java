package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    Integer ratingId;

    @Column(name = "moodys_rating")
    @NotBlank(message = "MoodysRating cannot be blank") // annotation de validation plutot pour type string
    String moodysRating;

    @Column(name = "sandp_rating")
    @NotBlank(message = "SanPRating cannot be blank")
    String sandPRating;

    @Column(name = "fitch_rating")
    @NotBlank(message = "FitchRating cannot be blank")
    String fitchRating;

    @Column(name = "order_number")
    @NotNull(message = "OrderNumber cannot be blank")  // annotation de validation plus large que @NotBlank
    Integer orderNumber;
}
