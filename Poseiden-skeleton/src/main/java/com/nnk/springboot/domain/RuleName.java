package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

//import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ruleId;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Json cannot be blank")
    private String json;

    @NotBlank(message = "Template cannot be blank")
    private String template;

    @NotBlank(message = "Sql_str cannot be blank")
    private String sqlStr;

    @NotBlank(message = "Sql_part cannot be blank")
    private String sqlPart;
}
