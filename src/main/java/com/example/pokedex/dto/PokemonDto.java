package com.example.pokedex.dto;

import com.example.pokedex.entities.Abilities;
import com.example.pokedex.entities.Types;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDto {
    @Id
    private String id;
    @JsonProperty("name")
    private String name;
    private String height;
    private String weight;
    private List<Abilities> abilities;
    private List<Types> types;
}
