package vn.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.internship;

@Repository
public interface IInternshipRepository extends JpaRepository<internship, Integer>{

}
