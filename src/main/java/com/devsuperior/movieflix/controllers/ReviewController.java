package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PreAuthorize("hasAnyRole('ROLE_MEMBER')")
    @PostMapping
    public ResponseEntity<ReviewDTO> save(@Valid @RequestBody ReviewDTO dto){
        ReviewDTO newDTO = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(newDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReviewDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
}
