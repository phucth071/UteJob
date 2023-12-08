package vn.hcmute.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.hcmute.entities.student;
import vn.hcmute.repository.IStudentRepository;
import vn.hcmute.services.IStudentService;
@Component
public class StudentServiceImpl implements IStudentService{
	@Override
	public <S extends student> S save(S entity) {
		return studentRepo.save(entity);
	}

	@Autowired
	IStudentRepository studentRepo;

	@Override
	public List<student> findAll() {
		return studentRepo.findAll();
	}

	@Override
	public Optional<student> findById(Integer id) {
		return studentRepo.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		studentRepo.deleteById(id);
	}

	@Override
	public List<student> findByFirstnameContaining(String name) {
		// TODO Auto-generated method stub
		return studentRepo.findByFirstnameContaining(name);
	}
	
}
