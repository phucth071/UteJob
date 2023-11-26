package vn.hcmute.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hcmute.entities.company;
import vn.hcmute.repository.ICompanyRepository;
import vn.hcmute.services.ICompanyService;
@Service
public class CompanyServiceImpl implements ICompanyService{
	@Autowired
	ICompanyRepository companyRepo;

	public CompanyServiceImpl(ICompanyRepository companyRepo) {
		this.companyRepo = companyRepo;
	}

	@Override
	public <S extends company> S save(S entity) {
		return companyRepo.save(entity);
	}

	@Override
	public List<company> findAll() {
		return companyRepo.findAll();
	}

	@Override
	public long count() {
		return companyRepo.count();
	}

	@Override
	public void deleteById(Integer id) {
		companyRepo.deleteById(id);
	}

	public CompanyServiceImpl() {
		super();
	}

	@Override
	public void delete(company entity) {
		companyRepo.delete(entity);
	}

	public Optional<company> findById(Integer id) {
		return companyRepo.findById(id);
	}
	
	
	
}
