package vn.hcmute.services;

import java.util.List;
import java.util.Optional;

import vn.hcmute.entities.company;

public interface ICompanyService {

	void delete(company entity);

	void deleteById(Integer id);

	long count();

	List<company> findAll();

	<S extends company> S save(S entity);

	Optional<company> findById(Integer id);

}
