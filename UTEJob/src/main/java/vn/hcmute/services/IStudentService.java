package vn.hcmute.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hcmute.entities.student;
@Service
public interface IStudentService {

	void deleteById(Integer id);

	Optional<student> findById(Integer id);

	List<student> findAll();

	<S extends student> S save(S entity);

}
