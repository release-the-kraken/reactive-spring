package com.rtk.reactivespring.movie;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {
    private final List<Movie> movies = new ArrayList<>();

    {
        movies.add(new Movie(1L, "Blazing Saddles", "Funniest fart joke in movie history."));
        movies.add(new Movie(2L, "Supermen 3", "Not so super."));
    }

    Flux<Movie> movieFlux = Flux.fromIterable(movies);
    Flux<Movie> findAll(){
        return movieFlux;
    }

    Mono<Movie> findById(long id){
        return movieFlux
                .filter(movie -> movie.getId() == id)
                .single();
    }

    Mono<Movie> save(Movie movie) {
        movies.add(movie);
        return Mono.just(movie);
    }
}
