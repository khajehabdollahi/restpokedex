package com.example.pokedex.entities;

import org.springframework.data.annotation.Id;

public class Pokemon {
    private static final long serialVersionUID = 669636611022282531L;

    @Id
    private int id;
    private String name;
    private int height;
    private int base_stat;

    public Pokemon() {  }

    public Pokemon(int id, String name, int height, int base_stat) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.base_stat = base_stat;
    }


}
