package vn.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.skill;

@Repository
public interface ISkillRepository extends JpaRepository<skill, Integer>{

}
