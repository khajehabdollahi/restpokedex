package com.example.pokedex.controllers;

import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> findMovies(@RequestParam String title) {
        var movie = pokemonService.findAll(title);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findMovieById(@PathVariable String id) {
        return ResponseEntity.ok(pokemonService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> saveMovie(@RequestBody Pokemon movie) {
        var savedMovie = pokemonService.save(movie);
        var uri = URI.create("/api/v1/movies/" + savedMovie.getId()); // /api/v1/movies/1
        return ResponseEntity.created(uri).body(savedMovie);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMovie(@PathVariable String id, @RequestBody Movie movie) {
        pokemonService.update(id, movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable String id) {
        pokemonService.delete(id);
    }