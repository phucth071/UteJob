package vn.hcmute.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import vn.hcmute.entities.application;
import vn.hcmute.entities.internship;
import vn.hcmute.entities.review;
import vn.hcmute.entities.student;
import vn.hcmute.repository.IApplicationRepository;
import vn.hcmute.repository.IInternshipRepository;
import vn.hcmute.repository.IReviewRepository;
import vn.hcmute.repository.IStudentRepository;
import vn.hcmute.services.IReviewService;
@Service
public class ReviewServiceImpl implements IReviewService{
	@Autowired
	IReviewRepository reviewRepo;
	IApplicationRepository applicationRepo;

	@Override
	public <S extends review> S save(S entity) {
		
		return reviewRepo.save(entity);
	}

	@Override
	public List<review> findAll() {
		return reviewRepo.findAll();
	}

	@Override
	public Optional<review> findById(Integer id) {
		return reviewRepo.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		reviewRepo.deleteById(id);
	}

	@Override
	public List<review> findByStatusContaining(String name) {
		// TODO Auto-generated method stub
		return reviewRepo.findByStatusContaining(name);
	}
	@Autowired
    public ReviewServiceImpl(IReviewRepository reviewRepository, IApplicationRepository applicationRepository) {
        this.reviewRepo = reviewRepository;
        this.applicationRepo = applicationRepository;
    }

    @Override
    public review createReview(Integer applicationId, String comment, int rating) {
        application application = applicationRepo.findById(applicationId)
            .orElseThrow(() -> new EntityNotFoundException("Không tin thấy id: " + applicationId));

        review review = new review();
        review.setApplication(application);
        review.setComment(comment);
        review.setRating(rating);

        return reviewRepo.save(review);
    }

    @Override
    public List<review> getReviewsByApplication(Integer applicationId) {
        return reviewRepo.findByApplication_ApplicationId(applicationId);
    }
	

	

	
}
