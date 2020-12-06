package com.example.pokedex.controllers;

import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.services.PokemonConsumerService;
import com.example.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private PokemonConsumerService pokemonConsumerService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> findPokemons(@RequestParam(required = false) String name,
                                                      @RequestParam(required = false) Integer weight) {
        var pokemon = pokemonService.findAll(name, weight);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findPokemonById(@PathVariable String id) {
        return ResponseEntity.ok(pokemonService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
        var savedPokemon = pokemonService.save(pokemon);
        var uri = URI.create("/api/v1/pokemons/" + savedPokemon.getId());
        return ResponseEntity.created(uri).body(savedPokemon);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePokemon(@PathVariable String id, @RequestBody Pokemon pokemon) {
        pokemonService.update(id, pokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable String id) {
        pokemonService.delete(id);
    }


}