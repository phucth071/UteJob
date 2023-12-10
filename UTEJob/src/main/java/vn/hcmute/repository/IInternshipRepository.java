package vn.hcmute.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.application;
import vn.hcmute.entities.internship;

@Repository
public interface IInternshipRepository extends JpaRepository<internship, Integer> {
	
	@Query("SELECT c.job_nature FROM internship c WHERE c.internship_id = :name")
	String findInternshipByInternshipId(@Param("name") int internshipId);
	
	List<internship> findByStatus(boolean status);
	
	@Query("SELECT a FROM internship a WHERE a.status = :status")
	Page<internship> findByStatus(Boolean status, Pageable page);

	@Query("SELECT c FROM internship c")
	Page<internship> findAll(Pageable pageable);

}
