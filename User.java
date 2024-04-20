package com.example.demo.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class User {
    private int id;

    @NotNull
    @NotEmpty
    @Length(min = 5, max = 100)
    private String name;

    @NotNull
    @NotEmpty
    private String email;
}
