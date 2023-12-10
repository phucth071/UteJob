package vn.hcmute.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import vn.hcmute.entities.student;
import vn.hcmute.repository.IStudentRepository;
import vn.hcmute.services.IStudentService;
@Component
@Service
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
	public Page<student> findByStudentName(String name, Pageable page) {
		return studentRepo.findByStudentName(name, page);
	}

	@Override
	public Page<student> findAll(Pageable pageable) {
		return studentRepo.findAll(pageable);
	}

	@Override
	public long count() {
		return studentRepo.count();
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

	@Override
	public String findStudentNameByStudentId(int studentId) {
		// TODO Auto-generated method stub
		return studentRepo.findStudentNameByStudentId(studentId);
	}

	
	
}
