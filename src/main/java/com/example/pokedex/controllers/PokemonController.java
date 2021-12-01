package com.example.pokedex.controllers;

import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping("/search/pokemons")
    public Pokemon getPokemons(String name) {
        return pokemonService.searchPokemon(name);
    }

    @GetMapping("/api/pokemons")
    public List<Pokemon> getMyPokemons(){
        return pokemonService.getAllMyPokemons();
    }

    @PostMapping("/api/pokemons")
    public Pokemon saveMyPokemon(@RequestBody Pokemon pokemon){
        return pokemonService.savePokemon(pokemon);
    }
}