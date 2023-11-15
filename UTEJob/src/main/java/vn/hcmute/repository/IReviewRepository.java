package vn.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.review;

@Repository
public interface IReviewRepository extends JpaRepository<review, Integer>{

}
