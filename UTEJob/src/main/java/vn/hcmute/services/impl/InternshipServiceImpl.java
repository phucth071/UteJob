package vn.hcmute.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hcmute.entities.internship;
import vn.hcmute.repository.IInternshipRepository;
import vn.hcmute.services.IInternshipService;
@Service
public class InternshipServiceImpl implements IInternshipService{
	@Override
	public <S extends internship> S save(S entity) {
		return internshipRepo.save(entity);
	}

	@Override
	public List<internship> findAll() {
		return internshipRepo.findAll();
	}

	@Override
	public Optional<internship> findById(Integer id) {
		return internshipRepo.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		internshipRepo.deleteById(id);
	}
	

	@Autowired
	IInternshipRepository internshipRepo;


	@Override
	public List<internship> findByStatus(boolean status) {
		// TODO Auto-generated method stub
		return internshipRepo.findByStatus(status);
	}


	


	
}
