package vn.hcmute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import vn.hcmute.entities.student;

@Repository
public interface IStudentRepository extends JpaRepository<student, Integer>{
	@Query("SELECT c FROM student c WHERE c.first_name LIKE %:name%")
	List<student> findByFirstnameContaining(@Param("name") String name);

}
