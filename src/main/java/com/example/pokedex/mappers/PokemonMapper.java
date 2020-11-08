package com.example.pokedex.mappers;


import com.example.pokedex.dto.PokemonDto;
import com.example.pokedex.entities.Pokemon;
import org.mapstruct.Mapper;

@Mapper(uses = {StringToListMapper.class})
public interface PokemonMapper {
    Pokemon movieDtoToMovie(PokemonDto pokemonDto);
    PokemonDto movieToMovieDto(Pokemon movie);
}
