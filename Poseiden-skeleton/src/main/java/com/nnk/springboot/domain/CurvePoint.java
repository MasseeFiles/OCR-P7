package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull(message = "Curve id cannot be blank")
    Integer curveId;

    @NotNull(message = "As of date cannot be blank")
    Timestamp asOfDate;

    @NotNull(message = "Term cannot be blank")
    Double term;

    @NotNull(message = "Value cannot be blank")
    Double value;

    @NotNull(message = "Creation date cannot be blank")
    Timestamp creationDate;
}
