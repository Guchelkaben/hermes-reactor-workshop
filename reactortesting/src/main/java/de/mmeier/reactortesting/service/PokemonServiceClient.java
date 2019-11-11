package de.mmeier.reactortesting.service;

import de.mmeier.reactortesting.domain.PokemonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(path = "/api/v2/", url = "https://pokeapi.co", value = "pokemon")
public interface PokemonServiceClient {

    @GetMapping(path="pokemon/{id}")
    PokemonResponse getPokemonById(@PathVariable String id);

}
