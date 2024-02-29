package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity     // placement ici de l'annotation @Entity pour lisibilité du code
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "rating_id") // necessaire uniquement si on veut imposer un nom de table particulier
    private Integer ratingId;

    @NotBlank(message = "MoodysRating cannot be blank") // annotation de validation plutot pour type string
    private String moodysRating;

    @NotBlank(message = "SanPRating cannot be blank")
    private String sandPRating;

    @NotBlank(message = "FitchRating cannot be blank")
    private String fitchRating;

    @NotNull(message = "Order number cannot be blank")// annotation de validation plus large que @NotBlank
    @Max(100000)
    @Min(0)
    private Integer orderNumber;
}
