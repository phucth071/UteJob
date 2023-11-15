package vn.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.application;

@Repository
public interface IApplicationRepository extends JpaRepository<application, Integer>{

}
