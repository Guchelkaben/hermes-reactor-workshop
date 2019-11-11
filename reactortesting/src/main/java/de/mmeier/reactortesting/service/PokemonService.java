package de.mmeier.reactortesting.service;

import de.mmeier.reactortesting.domain.Pokemon;
import de.mmeier.reactortesting.domain.PokemonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonServiceClient psc;

    public Optional<Pokemon> getPokemonById(String id) {
        return Optional.of(psc.getPokemonById(id)).map(this::mapPokemonResponseToPokemon);
    }

    public Mono<Pokemon> getPokemonByReactorId(String id) {

        return Mono.just(mapPokemonResponseToPokemon(psc.getPokemonById(id)));
    }

    private Pokemon mapPokemonResponseToPokemon(PokemonResponse pokemonResponse) {
        return Pokemon.builder()
                .height(pokemonResponse.getHeight())
                .name(pokemonResponse.getName())
                .id(Integer.toString(pokemonResponse.getId()))
                .build();
    }
}