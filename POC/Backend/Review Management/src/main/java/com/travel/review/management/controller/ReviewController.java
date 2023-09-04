package com.travel.review.management.controller;

import com.travel.review.management.bean.response.ServiceResponseBean;
import com.travel.review.management.entity.ReviewEntity;
import com.travel.review.management.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    public ReviewService reviewService;

    @GetMapping("/find-by-id/{reviewUniqueId}")
    public ServiceResponseBean findById(@PathVariable("reviewUniqueId") String reviewUniqueId) {
        return this.reviewService.findById(reviewUniqueId);
    }

    @GetMapping("/find-all")
    public ServiceResponseBean findAll() {
        return this.reviewService.findAll();
    }

    @PostMapping("/add")
    public ServiceResponseBean addUser(@RequestBody ReviewEntity reviewEntity) {
        return this.reviewService.addReview(reviewEntity);
    }

    @PutMapping("/update")
    public ServiceResponseBean updateUser(@RequestBody ReviewEntity reviewEntity) {
        return this.reviewService.updateReview(reviewEntity);
    }

    @DeleteMapping("/delete/{reviewUniqueId}")
    public ServiceResponseBean deleteUser(@PathVariable("reviewUniqueId") String reviewUniqueId) {
        return this.reviewService.deleteReview(reviewUniqueId);
    }
}
