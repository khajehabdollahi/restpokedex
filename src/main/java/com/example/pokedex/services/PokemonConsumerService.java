package com.example.pokedex.services;

import com.example.pokedex.dto.PokemonDto;
import com.example.pokedex.entities.Pokemon;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

public class PokemonConsumerService {
    private final RestTemplate restTemplate;
    private String url ="https://pokeapi.co/api/v2/pokemon/";

    public PokemonConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public PokemonDto search(String name) {
        var urlWithTitleQuery = url + name;
        var pokemon = restTemplate.getForObject(urlWithTitleQuery, PokemonDto.class);
        if(pokemon == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found.");
        }
        return pokemon;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
