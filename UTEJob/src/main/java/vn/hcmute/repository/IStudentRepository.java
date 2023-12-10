package vn.hcmute.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.student;

@Repository
public interface IStudentRepository extends JpaRepository<student, Integer>{
	@Query("SELECT c FROM student c WHERE c.first_name LIKE %:name%")
	List<student> findByFirstnameContaining(@Param("name") String name);
	
	@Query("SELECT c.first_name FROM student c WHERE c.student_id = :name")
	String findStudentNameByStudentId(@Param("name") int studentId);


	@Query("SELECT c FROM student c WHERE c.first_name LIKE %:name%")
	Page<student> findByStudentName(@Param("name") String name, Pageable page);

    @Query("SELECT c FROM student c")
    Page<student> findAll(Pageable pageable);
}
