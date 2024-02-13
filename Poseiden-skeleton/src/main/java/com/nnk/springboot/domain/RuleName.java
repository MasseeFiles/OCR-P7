package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer ruleId;

    @NotBlank(message = "Name cannot be blank")
    String name;

    @NotBlank(message = "Description cannot be blank")
    String description;

    @NotBlank(message = "Json cannot be blank")
    String json;

    @NotBlank(message = "Template cannot be blank")
    String template;

    @NotBlank(message = "Sql_str cannot be blank")
    String sqlStr;

    @NotBlank(message = "Sql_part cannot be blank")
    String sqlPart;
}
