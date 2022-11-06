package com.rtk.reactivespring.config;

import com.rtk.reactivespring.movie.MovieHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutingConfiguration {

    @Bean
    RouterFunction<ServerResponse> routes(MovieHandler movieHandler){
        return route(GET("/movies"), movieHandler::listAllMovies)
                .andRoute( GET("/movies/{movieId}"), movieHandler::getMovieById)
                .andRoute(POST("/movies"), movieHandler::addMovie);
    }
}
