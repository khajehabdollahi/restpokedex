package com.example.pokedex.services;

import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.mappers.PokemonMapper;
import com.example.pokedex.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    private final PokemonRepository pokemonRepository;
    @Autowired
    private final PokemonConsumerService pokemonConsumerService;
    @Autowired
    private final PokemonMapper pokemonMapper;


    public List<Pokemon> findAll(String title) {
        var pokemons = pokemonRepository.findAll();
        pokemons = pokemons.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
        if(pokemons.isEmpty()) {
            var pokemonDto = pokemonConsumerService.search(title);
            if(pokemonDto != null) {
                /*var movie = new Movie(moviesDto.getTitle(), moviesDto.getPlot(), moviesDto.getLanguage(),
                        moviesDto.getCountry(), moviesDto.getYear(), moviesDto.getImdbID(), List.of(moviesDto.getActors()));*/
                var pokemon = pokemonMapper.movieDtoToMovie(pokemonDto);
                pokemons.add(this.save(pokemon));
            }
        }
        return pokemons;
    }

    public Pokemon findById(String id) {
        return pokemonRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta filmen"));
    }

    @CachePut(value = "pokemoneCache", key = "#result.id")
    public Pokemon save(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @CachePut(value = "pokemoneCache", key = "#id")
    public void update(String id, Pokemon pokemon) {
        if(!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta pokemon");
        }
        pokemon.setId(id);
        pokemonRepository.save(pokemon);
    }

    @CacheEvict(value = "pokemoneCache", allEntries = true)
    public void delete(String id) {
        if(!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta pokemon");
        }
        pokemonRepository.deleteById(id);
    }
}
