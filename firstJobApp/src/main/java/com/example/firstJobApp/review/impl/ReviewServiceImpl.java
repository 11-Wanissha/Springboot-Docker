package com.example.firstJobApp.review.impl;

import com.example.firstJobApp.company.Company;
import com.example.firstJobApp.company.CompanyService;
import com.example.firstJobApp.review.Review;
import com.example.firstJobApp.review.ReviewRepository;
import com.example.firstJobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private final CompanyService  companyService;
    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }


    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.getById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> allReview = getAllReviews(companyId);
         return allReview.stream()
                 .filter(review -> review.getId().equals(reviewId))
                 .findFirst()
                 .orElse(null);
    }
    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
         if (companyService.getById(companyId) != null) {
             review.setCompany(companyService.getById(companyId));
             review.setId(reviewId);
             reviewRepository.save(review);
             return true;
         }
         return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (getReview(companyId, reviewId) != null) {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

}
