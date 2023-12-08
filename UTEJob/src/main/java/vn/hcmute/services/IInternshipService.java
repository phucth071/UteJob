package vn.hcmute.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import vn.hcmute.entities.internship;

public interface IInternshipService {

	void deleteById(Integer id);

	Optional<internship> findById(Integer id);

	List<internship> findAll();

	<S extends internship> S save(S entity);

    List<internship> findByStatus(boolean status);

}
