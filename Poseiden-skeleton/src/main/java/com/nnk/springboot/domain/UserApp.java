package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @NotBlank(message = "User name is mandatory")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[!?@#$%^&*])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Password strength is too low : please use at least 8 characters, one capital letter, one digit and one special character")
    private String password;

    @NotBlank(message = "Full name is mandatory")
    private String fullName;

    @NotBlank(message = "Role is mandatory")
    private String role;
}
