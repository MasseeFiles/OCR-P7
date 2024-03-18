package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ratingId;

    @NotBlank(message = "MoodysRating cannot be blank")
    private String moodysRating;

    @NotBlank(message = "SanPRating cannot be blank")
    private String sandPRating;

    @NotBlank(message = "FitchRating cannot be blank")
    private String fitchRating;

    @NotNull(message = "Order number cannot be blank")
    @Max(100000)
    @Min(0)
    private Integer orderNumber;
}
