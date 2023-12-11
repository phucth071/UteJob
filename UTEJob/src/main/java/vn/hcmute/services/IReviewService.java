package vn.hcmute.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import vn.hcmute.entities.review;

public interface IReviewService {

	void deleteById(Integer id);

	Optional<review> findById(Integer id);

	List<review> findAll();

	<S extends review> S save(S entity);

	List<review> findByStatusContaining(@Param("name") String name);

	review createReview(Integer application_id, String comment, int rating);
	
    List<review> getReviewsByApplication(Integer application_id);

}
