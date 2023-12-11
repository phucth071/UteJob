package vn.hcmute.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
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
	 @Override
	    public student updateStudent(Integer studentId, student updatedStudent) {
		 	student existingStudent = studentRepo.findById(studentId)
	            .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

	        // Cập nhật thông tin sinh viên
		 	existingStudent.setFirst_name(updatedStudent.getFirst_name());
	        existingStudent.setLast_name(updatedStudent.getLast_name());
	        existingStudent.setMajor(updatedStudent.getMajor());
	        existingStudent.setUser_id(updatedStudent.getUser_id());
	        existingStudent.setAvatar(updatedStudent.getAvatar());
	        existingStudent.setImageFile(updatedStudent.getImageFile());

	        return studentRepo.save(existingStudent);
	    }
	
}
