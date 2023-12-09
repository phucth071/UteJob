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
	@Autowired
	IInternshipRepository internshipRepo;

	public InternshipServiceImpl(IInternshipRepository internshipRepo) {
		
		this.internshipRepo = internshipRepo;
	}

	@Override
	public <S extends internship> S save(S entity) {
		return internshipRepo.save(entity);
	}

	@Override
	public List<internship> findAll() {
		return internshipRepo.findAll();
	}

	@Override
	public List<internship> findAllById(Iterable<Integer> ids) {
		return internshipRepo.findAllById(ids);
	}

	@Override
	public Optional<internship> findById(Integer id) {
		return internshipRepo.findById(id);
	}

	@Override
	public long count() {
		return internshipRepo.count();
	}

	@Override
	public void deleteById(Integer id) {
		internshipRepo.deleteById(id);
	}

	@Override
	public List<internship> findByJobnature(String job_nature) {
		return internshipRepo.findByJobnature(job_nature);
	}

	
	

}
