package com.example.pokedex.controllers;

import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/pokemons")
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping
    public Pokemon getPokemons(String name){
        return pokemonService.searchPokemon(name);
    }
}