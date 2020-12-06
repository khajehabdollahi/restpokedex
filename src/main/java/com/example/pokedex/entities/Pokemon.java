package com.example.pokedex.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Pokemon {
    private static final long serialVersionUID = 669636611022282531L;

    @Id
    private String id;
    private String name;
    private int height;
    private int base_stat;

    public Pokemon() {
    }

    public Pokemon(String id, String name, int height, int base_stat) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.base_stat = base_stat;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }
}
