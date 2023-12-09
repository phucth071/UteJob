package vn.hcmute.services;

import java.util.List;
import java.util.Optional;

import vn.hcmute.entities.internship;

public interface IInternshipService {

	void deleteById(Integer id);

	Optional<internship> findById(Integer id);

	List<internship> findAllById(Iterable<Integer> ids);

	List<internship> findAll();

	<S extends internship> S save(S entity);

	List<internship> findByJobnature(String job_nature);
	
	List<internship> findByStatus(boolean status);
}
