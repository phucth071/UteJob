package vn.hcmute.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import vn.hcmute.repository.IApplicationRepository;
import vn.hcmute.services.IApplicationService;

public class ApplicationServiceImpl implements IApplicationService{
	@Autowired
	IApplicationRepository applicationRepo;
}
