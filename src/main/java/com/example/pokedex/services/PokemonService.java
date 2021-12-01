package com.example.pokedex.services;

import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PokemonService {
    private static String URL = "https://pokeapi.co/api/v2/pokemon/";
    @Autowired
    private PokemonRepository pokemonRepository;

    private final RestTemplate restTemplate;

    public PokemonService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Pokemon searchPokemon(String name){
        var urlWithName = URL + name;
        Pokemon pokemon= restTemplate.getForObject(urlWithName, Pokemon.class);
        if (pokemon == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found!");
        }
        return pokemon;
    }

    public List<Pokemon> getAllMyPokemons(){
        return pokemonRepository.findAll();
    }

    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }
}