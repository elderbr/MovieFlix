package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MovieWithReviewDTO {

    private Long id;
    private String title;
    private String subTitle;
    private Integer movieYear;
    private String imgUrl;
    private String synopsis;

    private GenreDTO genre;
    private List<ReviewDTO> reviews = new ArrayList<>();

    public MovieWithReviewDTO() {
    }

    public MovieWithReviewDTO(Movie entity) {
        id = entity.getId();
        title = entity.getTitle();
        subTitle = entity.getSubTitle();
        movieYear = entity.getYear();
        imgUrl = entity.getImgUrl();
        synopsis = entity.getSynopsis();
        genre = entity.getGenre().parseToDTO();

        if (!entity.getGenre().getMovies().isEmpty()) {
            for (Review review : entity.getReviews()) {
                reviews.add(review.parseToDTO());
            }
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public Integer getMovieYear() {
        return movieYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public Movie parseToEntity() {
        Movie entity = new Movie();
        entity.setId(id);
        entity.setTitle(title);
        entity.setSubTitle(subTitle);
        entity.setYear(movieYear);
        entity.setImgUrl(imgUrl);
        entity.setSynopsis(synopsis);
        entity.setGenre(genre.parseToEntity());

        for (ReviewDTO review : reviews) {
            entity.addReview(review.parseToEnity());
        }
        return entity;
    }
}
