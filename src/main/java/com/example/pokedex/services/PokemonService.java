package com.example.pokedex.services;

import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.mappers.PokemonMapper;
import com.example.pokedex.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private PokemonConsumerService pokemonConsumerService;

    private PokemonMapper pokemonMapper;

    public List<Pokemon> findAll(String name, int height) {
        var all_pokemons = pokemonRepository.findAll();
        if (name!=null) searchPokemonByName(name, all_pokemons);
        if (height>0) searchPokemonByHeight(height, all_pokemons);
        return all_pokemons;
    }

    private List<Pokemon> searchPokemonByName(String name, List<Pokemon> pokemons){
        var allpokemons = pokemonRepository.findAll();
        allpokemons = allpokemons.stream()
                .filter(pokemon -> pokemon.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        if(pokemons.isEmpty()) {
            var pokemonDto = pokemonConsumerService.searchPokemon(name);
            if(pokemonDto != null) {
                var pokemon = new Pokemon(pokemonDto.getId(), pokemonDto.getName(), pokemonDto.getHeight(), pokemonDto.getBase_stat());
                pokemons.add(this.save(pokemon));
            }
        }
        return pokemons;
    }

    private List<Pokemon> searchPokemonByHeight(Integer height, List<Pokemon> pokemons){
        pokemons = pokemons.stream()
                .filter(pokemon -> pokemon.getHeight()==height)
                .collect(Collectors.toList());
        if(pokemons.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta pokemonen med den weight");
        }
        return pokemons;
    }

    public Pokemon findById(String id) {
        return pokemonRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta pokemonen"));
    }

    public Pokemon save(Pokemon movie) {
        return pokemonRepository.save(movie);
    }

    public void update(String id, Pokemon pokemon) {
        if(!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta pokemonen");
        }
        pokemon.setId(id);
        pokemonRepository.save(pokemon);
    }

    public void delete(String id) {
        if(!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta pokemonen");
        }
        pokemonRepository.deleteById(id);
    }
}
