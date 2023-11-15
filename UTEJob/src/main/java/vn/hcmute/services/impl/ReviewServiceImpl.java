package vn.hcmute.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import vn.hcmute.repository.IReviewRepository;
import vn.hcmute.services.IReviewService;

public class ReviewServiceImpl implements IReviewService{
	@Autowired
	IReviewRepository reviewRepo;
}
