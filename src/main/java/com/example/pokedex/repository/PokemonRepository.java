package com.example.pokedex.repository;

import com.example.pokedex.entities.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PokemonRepository extends MongoRepository<Pokemon, String> {
}
