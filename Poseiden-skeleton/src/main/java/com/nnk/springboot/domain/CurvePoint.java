package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer curveId;

    private Timestamp asOfDate;

    @NotNull(message = "Curve point cannot be blank")
    @DecimalMax("100000.00")
    @DecimalMin("0.00")
    private Double term;

    @NotNull(message = "Curve point cannot be blank")
    @DecimalMax("100000.00")
    @DecimalMin("0.00")
    private Double value;

    private Timestamp creationDate;
}
