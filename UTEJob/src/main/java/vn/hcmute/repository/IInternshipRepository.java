package vn.hcmute.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.hcmute.entities.internship;
import vn.hcmute.entities.users;

import java.util.List;

@Repository
public interface IInternshipRepository extends JpaRepository<internship, Integer>{
	
    List<internship> findByStatus(boolean status);
    List<internship> findByJobnature(String jobnature);

}
