package de.mmeier.reactortesting.web;

import de.mmeier.reactortesting.domain.Pokemon;
import de.mmeier.reactortesting.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.reflect.DeferredResolvedPointcutDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequestMapping(path = "/pokemoncenter/")
@RestController
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping(path = "pokemons/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable String id) {
        return pokemonService.getPokemonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build());
    }

    @GetMapping(path = "pokeflux/{id}")
    public Mono<Pokemon> getPokemonByReactorId(@PathVariable String id) {
        return Mono.fromCallable(() -> pokemonService.getPokemonById(id))
                .map(o -> o.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry bro")))
                .tag
                .subscribeOn(Schedulers.boundedElastic());
    }
}
