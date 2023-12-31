package vn.hcmute.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import vn.hcmute.entities.student;

@Service
public interface IStudentService {

	void deleteById(Integer id);

	Optional<student> findById(Integer id);

	List<student> findAll();

	<S extends student> S save(S entity);

	List<student> findByFirstnameContaining(@Param("name") String name);

	long count();

	Page<student> findAll(Pageable pageable);

	Page<student> findByStudentName(String name, Pageable page);

	String findStudentNameByStudentId(@Param("name") int studentId);

}
