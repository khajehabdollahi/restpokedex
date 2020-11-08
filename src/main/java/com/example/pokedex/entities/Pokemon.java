package com.example.pokedex.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {
    private static final long serialVersionUID = 669636611022282531L;

    @Id
    private int id;
    private String name;
    private int height;
    private int base_stat;


}
