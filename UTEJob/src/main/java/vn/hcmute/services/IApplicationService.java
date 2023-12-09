package vn.hcmute.services;

import java.util.List;
import java.util.Optional;

import vn.hcmute.entities.application;

public interface IApplicationService {

	void deleteById(Integer id);

	Optional<application> findById(Integer id);

	List<application> findAll();

	<S extends application> S save(S entity);

	List<application> findByStatus(boolean status);

}
