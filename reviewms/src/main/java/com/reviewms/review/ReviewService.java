package com.reviewms.review;



import java.util.List;

public interface ReviewService {
    boolean createReview(Long companyId, Review review);
    List<Review> getAllReviews(Long companyId);
    boolean updateReview(Long reviewId, Review review);
    boolean deleteReview(Long reviewId);
    Review getReviewById(Long reviewId);
}
