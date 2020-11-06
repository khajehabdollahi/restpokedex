package com.example.pokedex.dto;

public class PokemonDto {

     private String name;
    private int height;
    private int base_stat;

    public PokemonDto() {
    }

    public PokemonDto(String name, int height, int base_stat) {
        this.name = name;
        this.height = height;
        this.base_stat = base_stat;
    }
}
