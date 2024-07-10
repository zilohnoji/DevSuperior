package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.ReviewInsertDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final AuthService authService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository, AuthService authService) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.authService = authService;
    }

    public ReviewDTO insert(ReviewDTO dto) {

        Review entity = new Review();
        User authenticated = authService.authenticated();

        entity.setText(dto.getText());
        entity.setMovie(movieRepository.findById(dto.getMovieId()).get());
        entity.setUser(authenticated);

        return toDto(reviewRepository.save(entity));
    }

    private ReviewDTO toDto(Review entity) {
        ReviewDTO response = new ReviewDTO();

        response.setId(entity.getId());
        response.setText(entity.getText());
        response.setMovieId(entity.getMovie().getId());
        response.setUserId(entity.getUser().getId());
        response.setUserName(entity.getUser().getUsername());
        response.setUserEmail(entity.getUser().getEmail());

        return response;
    }
}