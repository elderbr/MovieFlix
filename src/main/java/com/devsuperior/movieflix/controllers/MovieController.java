package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.MovieWithReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_VISITOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_VISITOR')")
    @GetMapping
    public ResponseEntity<Page<MovieDetailsDTO>> findByMovieWithByIdGenre(@RequestParam(value = "genreId", defaultValue = "0") Long genreId, Pageable pageable) {
        return ResponseEntity.ok(service.findByMovieWithByIdGenrePageable(genreId, pageable));
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<Page<MovieWithReviewDTO>> findByMovieWithByIdReview(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(service.findByMovieWithByIdReviewPageable(id, pageable));
    }
}
