package vn.hcmute.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import vn.hcmute.entities.application;

public interface IApplicationService {

	void deleteById(Integer id);

	Optional<application> findById(Integer id);

	List<application> findAll();

	<S extends application> S save(S entity);

	List<application> findByStatus(boolean status);

	long count();

	Page<application> findAll(Pageable pageable);

	Page<application> findByStatus(boolean status, Pageable page);


	

	

}
