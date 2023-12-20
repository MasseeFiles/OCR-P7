package com.nnk.springboot.domain;

import jakarta.persistence.*;

//import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_name_id")
    Integer id;
//    String name;
//    String description;
//    String json;
//    String template;
//    String sqlStr;
//    String sqlPart;
}
