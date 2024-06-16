package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.MovieWithReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = """
            SELECT new com.devsuperior.movieflix.dto.MovieDetailsDTO(m) FROM Movie m
            JOIN FETCH m.genre
            ORDER BY m.title
            """,
            countQuery = """
                    SELECT m FROM Movie m
                    JOIN FETCH m.genre
                    ORDER BY m.title
                    """
    )
    Page<MovieDetailsDTO> findByMovieWithByIdGenrePageable(Pageable pageable);

    @Query(value = """
            SELECT new com.devsuperior.movieflix.dto.MovieDetailsDTO(m) FROM Movie m
            JOIN FETCH m.genre
            WHERE m.genre.id = :id
            ORDER BY m.title
            """,
            countQuery = """
                    SELECT m FROM Movie m
                    JOIN FETCH m.genre
                    WHERE m.genre.id = :id
                    ORDER BY m.title
                    """
    )
    Page<MovieDetailsDTO> findByMovieWithByIdGenrePageable(@Param("id") Long id, Pageable pageable);

    @Query(value = """
            SELECT
            new com.devsuperior.movieflix.dto.MovieWithReviewDTO(m)
            FROM Review r
            JOIN r.movie m
            JOIN m.genre g
            WHERE m.id = :id
            GROUP BY m.title
            ORDER BY m.title
            """,
            countQuery = """
                    SELECT
                    new com.devsuperior.movieflix.dto.MovieWithReviewDTO(m)
                    FROM Review r
                    JOIN r.movie m
                    JOIN m.genre g
                    WHERE m.id = :id
                    GROUP BY m.title
                    ORDER BY m.title
                    """
    )
    Page<MovieWithReviewDTO> findByMovieWithByIdReviewPageable(@Param("id") Long id, Pageable pageable);
}
