package vn.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.company;

@Repository
public interface ICompanyRepository extends JpaRepository<company, Integer>{

}
