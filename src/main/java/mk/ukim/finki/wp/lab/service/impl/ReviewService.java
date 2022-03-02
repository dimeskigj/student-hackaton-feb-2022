package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Review;
import mk.ukim.finki.wp.lab.repository.jpa.ReviewRepositoryDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements mk.ukim.finki.wp.lab.service.ReviewService {
    private final ReviewRepositoryDB reviewRepository;

    public ReviewService(ReviewRepositoryDB reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> listAll() {
        return null;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }


}
