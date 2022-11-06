package com.rtk.reactivespring.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    Flux<Movie> getAll(){
        return movieRepository.findAll();
    }

    Mono<Movie> getMovieById(long id){
        return movieRepository.findById(id);
    }

    Mono<Movie> addMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
