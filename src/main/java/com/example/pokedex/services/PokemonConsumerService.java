package com.example.pokedex.services;

import com.example.pokedex.dto.PokemonDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PokemonConsumerService {
    private final RestTemplate restTemplate;
    private String url ="https://pokeapi.co/api/v2/pokemon/";

    public PokemonConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public PokemonDto searchPokemon(String name) {
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
