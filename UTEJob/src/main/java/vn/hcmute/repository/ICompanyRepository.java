package vn.hcmute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.hcmute.entities.company;

@Repository
public interface ICompanyRepository extends JpaRepository<company, Integer>{
	
	@Query("SELECT c.company_name FROM company c WHERE c.company_id = :companyId")
    String findCompanyNameByCompanyId(@Param("companyId") int companyId);
	
	@Query("SELECT c FROM company c WHERE c.company_name LIKE %:name%")
	List<company> findByCompanyNameContaining(@Param("name") String name);

	
}
