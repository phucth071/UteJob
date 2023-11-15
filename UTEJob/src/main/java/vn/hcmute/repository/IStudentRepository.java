package vn.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.student;

@Repository
public interface IStudentRepository extends JpaRepository<student, Integer>{

}
