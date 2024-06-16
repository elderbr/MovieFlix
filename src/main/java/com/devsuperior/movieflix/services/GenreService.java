package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public GenreDTO findById(Long id){
        if(id < 1){
            throw new ResourceNotFoundException("Genre not found");
        }
        Genre entity = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found"));
        return entity.parseToDTO();
    }

    @Transactional(readOnly = true)
    public List<GenreDTO> findAll(){
        List<Genre> list = genreRepository.findAll();
        return list.stream().map(GenreDTO::new).toList();
    }
}
