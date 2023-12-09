package vn.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.hcmute.entities.internship;

import java.util.List;

@Repository
public interface IInternshipRepository extends JpaRepository<internship, Integer> {
	//@Query("SELECT u FROM internship u WHERE u.job_nature = :job_nature")
	//List<internship> getListIntershipByJobNature(@Param("job_nature") String job_nature);
	
	List<internship> findByJobnature(String jobnature);
}
