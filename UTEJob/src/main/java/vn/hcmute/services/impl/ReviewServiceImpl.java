package vn.hcmute.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hcmute.entities.review;
import vn.hcmute.repository.IReviewRepository;
import vn.hcmute.services.IReviewService;
@Service
public class ReviewServiceImpl implements IReviewService{
	@Autowired
	IReviewRepository reviewRepo;

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
}
