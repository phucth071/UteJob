package vn.hcmute.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import vn.hcmute.entities.company;

public interface ICompanyService {

	void delete(company entity);

	void deleteById(Integer id);

	long count();

	List<company> findAll();

	<S extends company> S save(S entity);

	Optional<company> findById(Integer id);

	String findCompanyNameByCompanyId(@Param("companyId") int companyId);

	List<company> findByCompanyNameContaining(@Param("name") String name);

	Page<company> findByCompanyName(@Param("name") String name, Pageable page);

	Page<company> findAll(Pageable pageable);


}
