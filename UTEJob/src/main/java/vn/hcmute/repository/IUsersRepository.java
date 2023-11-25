package vn.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.users;

@Repository
public interface IUsersRepository extends JpaRepository<users, Integer>{
	
}
