package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity     // placement ici de l'annotation @Entity pour lisibilité du code
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "rating_id") // necessaire uniquement si on veut imposer un nom de table particulier
    Integer ratingId;

    @NotBlank(message = "MoodysRating cannot be blank") // annotation de validation plutot pour type string
    String moodysRating;

    @NotBlank(message = "SanPRating cannot be blank")
    String sandPRating;

    @NotBlank(message = "FitchRating cannot be blank")
    String fitchRating;

    @NotNull(message = "OrderNumber cannot be blank")  // annotation de validation plus large que @NotBlank
    Integer orderNumber;
}
