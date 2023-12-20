package com.nnk.springboot.domain;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

//import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_naeme_id")
    Integer id;
//    Integer curveId;
//    Timestamp asOfDate;
//    Double term;
//    Double value;
//    Timestamp creationDate;

}
