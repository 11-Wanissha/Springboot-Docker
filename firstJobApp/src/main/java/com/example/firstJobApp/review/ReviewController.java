package com.example.firstJobApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/companies/{id}")
public class ReviewController {
    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.getAllReviews(id), HttpStatus.OK);
    }
    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long id, @RequestBody Review review) {
        boolean isCreated = reviewService.createReview(id, review);
        if(isCreated) {
            return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review isn't created", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long id, @PathVariable Long reviewId) {
        Review review = reviewService.getReview(id, reviewId);
        if (review != null) {
            return new ResponseEntity<>(reviewService.getReview(id, reviewId), HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long id, @PathVariable Long reviewId, @RequestBody Review review) {
        boolean isUpdated = reviewService.updateReview(id, reviewId, review);
        if (isUpdated) {
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review updated unsuccessfully", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id, @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(id, reviewId);
        if (isDeleted) {
            return new  ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        return  new ResponseEntity<>("Review deleted unsuccessfully", HttpStatus.NOT_FOUND);
    }


}
