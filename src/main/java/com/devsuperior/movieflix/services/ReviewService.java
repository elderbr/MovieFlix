package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO save(ReviewDTO dto){
        // Pegando dados do usuário logado
        User user = authService.authenticated();
        dto.setUserId(user.getId());
        dto.setUserName(user.getName());
        dto.setUserEmail(user.getEmail());

        Review entity = reviewRepository.save(dto.parseToEnity());
        entity.setUser(user);
        return entity.parseToDTO();
    }

    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id) {
        Review entity = reviewRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Review not found"));
        return entity.parseToDTO();
    }
}
