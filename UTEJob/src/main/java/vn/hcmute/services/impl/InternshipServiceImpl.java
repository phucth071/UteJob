package vn.hcmute.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import vn.hcmute.repository.IInternshipRepository;
import vn.hcmute.services.IInternshipService;

public class InternshipServiceImpl implements IInternshipService{
	@Autowired
	IInternshipRepository internshipRepo;
}
