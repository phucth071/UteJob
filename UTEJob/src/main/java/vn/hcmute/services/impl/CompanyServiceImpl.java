package vn.hcmute.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import vn.hcmute.repository.ICompanyRepository;
import vn.hcmute.services.ICompanyService;

public class CompanyServiceImpl implements ICompanyService{
	@Autowired
	ICompanyRepository companyRepo;
	
}
