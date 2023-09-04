package com.travel.review.management.service;

import com.travel.review.management.bean.response.ServiceResponseBean;
import com.travel.review.management.entity.ReviewEntity;
import com.travel.review.management.feign.IExperienceListingService;
import com.travel.review.management.feign.IUserManagementService;
import com.travel.review.management.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewService {

    public ServiceResponseBean findAll();

    public ServiceResponseBean findById(String reviewUniqueId);

    public ServiceResponseBean addReview(ReviewEntity reviewEntity);

    public ServiceResponseBean updateReview(ReviewEntity reviewEntity);

    public ServiceResponseBean deleteReview(String reviewUniqueId);
}
