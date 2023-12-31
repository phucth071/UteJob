package vn.hcmute.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import vn.hcmute.entities.internship;

@Service
public interface IInternshipService {

	void deleteById(Integer id);

	Optional<internship> findById(Integer id);

	List<internship> findAllById(Iterable<Integer> ids);

	List<internship> findAll();

	<S extends internship> S save(S entity);

	List<internship> findByJobnature(String job_nature);
	
	List<internship> findByStatus(boolean status);
	long count();

	Page<internship> findAll(Pageable pageable);

	Page<internship> findByStatus(boolean name, Pageable page);
	
	String findInternshipByInternshipId(@Param("name") int internshipId);

}
