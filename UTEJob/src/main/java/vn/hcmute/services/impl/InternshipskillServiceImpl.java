package vn.hcmute.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import vn.hcmute.repository.IInternshipskillRepository;
import vn.hcmute.services.IInternshipskillService;

public class InternshipskillServiceImpl implements IInternshipskillService{
	@Autowired
	IInternshipskillRepository internshipskillRepo;
}
