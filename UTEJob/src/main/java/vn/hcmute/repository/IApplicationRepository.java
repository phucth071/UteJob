package vn.hcmute.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.application;
import vn.hcmute.entities.company;


@Repository
public interface IApplicationRepository extends JpaRepository<application, Integer>{
	List<application> findByStatus(boolean status);
	
    @Query("SELECT a FROM application a WHERE a.status = :status")
	Page<application> findByStatus(boolean status, Pageable page);
	

    @Query("SELECT c FROM application c")
    Page<application> findAll(Pageable pageable);
	
}
