package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.MovieWithReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        if (id < 1) {
            throw new ResourceNotFoundException("Movie not found");
        }
        Movie entity = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        return entity.parseToDTO();
    }

    @Transactional(readOnly = true)
    public Page<MovieDetailsDTO> findByMovieWithByIdGenrePageable(Long idGenre, Pageable pageable) {
        if (idGenre > 0) {
            if (!genreRepository.existsById(idGenre)) {
                throw new ResourceNotFoundException("Movie not found");
            }
            return movieRepository.findByMovieWithByIdGenrePageable(idGenre, pageable);
        }
        return movieRepository.findByMovieWithByIdGenrePageable(pageable);
    }

    @Transactional(readOnly = true)
    public Page<MovieWithReviewDTO> findByMovieWithByIdReviewPageable(Long idReview, Pageable pageable) {
        return movieRepository.findByMovieWithByIdReviewPageable(idReview, pageable);
    }
}
