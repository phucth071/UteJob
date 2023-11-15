package vn.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.internshipskill;
import vn.hcmute.entities.internshipskill_id;


@Repository
public interface IInternshipskillRepository extends JpaRepository<internshipskill, internshipskill_id>{
}
