package com.example.pokedex.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
}
