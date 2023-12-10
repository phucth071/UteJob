package vn.hcmute.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.hcmute.entities.application;
import vn.hcmute.repository.IApplicationRepository;
import vn.hcmute.services.IApplicationService;
@Service
public class ApplicationServiceImpl implements IApplicationService{
	@Autowired
	IApplicationRepository applicationRepo;

	@Override
	public <S extends application> S save(S entity) {
		return applicationRepo.save(entity);
	}

	@Override
	public List<application> findAll() {
		return applicationRepo.findAll();
	}

	@Override
	public Optional<application> findById(Integer id) {
		return applicationRepo.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		applicationRepo.deleteById(id);
	}

	@Override
	public List<application> findByStatus(boolean status) {
		// TODO Auto-generated method stub
		return applicationRepo.findByStatus(status);
	}

	

	@Override
	public Page<application> findAll(Pageable pageable) {
		return applicationRepo.findAll(pageable);
	}

	@Override
	public long count() {
		return applicationRepo.count();
	}

	@Override
	public Page<application> findByStatus(boolean status, Pageable page) {
		// TODO Auto-generated method stub
		return applicationRepo.findByStatus(status, page);
	}

}
