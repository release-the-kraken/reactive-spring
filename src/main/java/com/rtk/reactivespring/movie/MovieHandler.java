package com.rtk.reactivespring.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class MovieHandler {

    private final MovieService movieService;

    public Mono<ServerResponse> listAllMovies(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movieService.getAll(), Movie.class);
    }

    public Mono<ServerResponse> getMovieById(ServerRequest request) {
        long movieId = Long.parseLong(request.pathVariable("movieId"));
        return movieService.getMovieById(movieId)
                .flatMap(movie -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(movie))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(NoSuchElementException.class, ex -> ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> addMovie(ServerRequest request) {
        Mono<Movie> movieMono = request.bodyToMono(Movie.class);
        return movieMono
                .flatMap(movie -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(movieService.addMovie(movie), Movie.class));
    }
}
