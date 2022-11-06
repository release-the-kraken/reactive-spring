package com.rtk.reactivespring;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class MovieRepository {
    Flux<Movie> findAll(){
        return Flux.just(new Movie(1L, "Blazing Saddles", "Funniest fart joke in movie history."),
                new Movie(2L, "Supermen 3", "Not so super."));
    }
}
