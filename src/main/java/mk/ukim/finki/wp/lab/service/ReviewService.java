package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> listAll();
    Review save(Review review);
}
