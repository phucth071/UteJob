package vn.hcmute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.review;

@Repository
public interface IReviewRepository extends JpaRepository<review, Integer>{
	@Query("SELECT c FROM review c WHERE c.comment LIKE %:name%")
	List<review> findByStatusContaining(@Param("name") String name);
	List<review> findByApplication_ApplicationId(Integer application_id);

}
