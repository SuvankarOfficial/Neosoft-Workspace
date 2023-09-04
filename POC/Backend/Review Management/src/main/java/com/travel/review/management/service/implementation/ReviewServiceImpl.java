package com.travel.review.management.service.implementation;

import com.travel.review.management.bean.response.ServiceResponseBean;
import com.travel.review.management.entity.ReviewEntity;
import com.travel.review.management.feign.IExperienceListingService;
import com.travel.review.management.feign.IUserManagementService;
import com.travel.review.management.global.exceptional.CannotBeNullException;
import com.travel.review.management.global.exceptional.DoesNotExistException;
import com.travel.review.management.repository.ReviewRepository;
import com.travel.review.management.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    public IUserManagementService userManagementService;

    @Autowired
    public IExperienceListingService experienceListingService;

    public ServiceResponseBean findAll(){
        List<ReviewEntity> reviewEntityList = this.reviewRepository.findAll();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(reviewEntityList).build();
    }

    public ServiceResponseBean findById(String reviewUniqueId) {
        if (reviewUniqueId == null)
            throw new CannotBeNullException("Review Id");
        Optional<ReviewEntity> reviewEntityOptional = this.reviewRepository.findByReviewUniqueId(reviewUniqueId);
        if (reviewEntityOptional.isEmpty())
            throw new DoesNotExistException("Review Id");
        ReviewEntity reviewEntity = reviewEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(reviewEntity).build();
    }

    public ServiceResponseBean addReview(ReviewEntity reviewEntity) {
        if (reviewEntity == null)
            throw new CannotBeNullException("Review Details");
        if(reviewEntity.getUserUniqueId() == null)
            throw new CannotBeNullException("User ID");
        if(!(this.userManagementService.existByUserManagementUniqueId(reviewEntity.getUserUniqueId())))
            throw new DoesNotExistException("User ID");
        if(reviewEntity.getExperienceUniqueId() == null)
            throw new CannotBeNullException("Experience ID");
        if(!(this.experienceListingService.existByExperienceListingUniqueId(reviewEntity.getExperienceUniqueId())))
            throw new DoesNotExistException("Experience ID");
        reviewEntity.setReviewUniqueId(UUID.randomUUID().toString());
        ReviewEntity savedReview = this.reviewRepository.save(reviewEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedReview).build();
    }

    public ServiceResponseBean updateReview(ReviewEntity reviewEntity) {
        if (reviewEntity == null)
            throw new CannotBeNullException("Review Details");
        if (reviewEntity.getReviewUniqueId() == null)
            throw new CannotBeNullException("Review Id");
        Optional<ReviewEntity> reviewEntityOptional = this.reviewRepository.findByReviewUniqueId(reviewEntity.getReviewUniqueId());
        if (reviewEntityOptional.isEmpty())
            throw new DoesNotExistException("Review Id");
        if(reviewEntity.getUserUniqueId() != null)
            if(!(this.userManagementService.existByUserManagementUniqueId(reviewEntity.getUserUniqueId())))
                throw new DoesNotExistException("User ID");
        reviewEntity = updateReviewDatabaseToNew(reviewEntity, reviewEntityOptional.get());
        ReviewEntity savedReview = this.reviewRepository.save(reviewEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedReview).build();
    }

    private ReviewEntity updateReviewDatabaseToNew(ReviewEntity reviewEntity, ReviewEntity databaseReviewEntity) {
        return databaseReviewEntity.builder()
                .reviewUniqueId(reviewEntity.getReviewUniqueId() != null ? reviewEntity.getReviewUniqueId() : databaseReviewEntity.getReviewUniqueId())
                .userUniqueId(reviewEntity.getUserUniqueId() != null ? reviewEntity.getUserUniqueId() : databaseReviewEntity.getUserUniqueId())
                .experienceUniqueId(reviewEntity.getExperienceUniqueId() != null ? reviewEntity.getExperienceUniqueId() : databaseReviewEntity.getExperienceUniqueId())
                .rating(reviewEntity.getRating() != null ? reviewEntity.getRating() : databaseReviewEntity.getRating())
                .comment(reviewEntity.getComment() != null ? reviewEntity.getComment() : databaseReviewEntity.getComment())
                .date(reviewEntity.getDate() != null ? reviewEntity.getDate() : databaseReviewEntity.getDate())
                .status(reviewEntity.getStatus() != null ? reviewEntity.getStatus() : databaseReviewEntity.getStatus())
                .build();
    }

    public ServiceResponseBean deleteReview(String reviewUniqueId) {
        if (reviewUniqueId == null)
            throw new CannotBeNullException("Review Id");
        Optional<ReviewEntity> reviewEntityOptional = this.reviewRepository.findByReviewUniqueId(reviewUniqueId);
        if(reviewEntityOptional.isEmpty())
           throw new DoesNotExistException("Review Id");
        ReviewEntity reviewEntity = reviewEntityOptional.get();
        reviewEntity.setStatus(Boolean.FALSE);
        ReviewEntity savedReview = this.reviewRepository.save(reviewEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedReview).build();
    }
}
